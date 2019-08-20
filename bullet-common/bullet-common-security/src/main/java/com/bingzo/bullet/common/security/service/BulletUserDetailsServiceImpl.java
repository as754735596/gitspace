/*
 *    Copyright (c) 2018-2025, bingzo All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the bingzo.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: bingzo (service@bingzo.cn)
 */

package com.bingzo.bullet.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.bingzo.bullet.admin.api.dto.UserInfo;
import com.bingzo.bullet.admin.api.entity.SysUser;
import com.bingzo.bullet.common.core.constant.CacheConstants;
import com.bingzo.bullet.common.core.constant.CommonConstants;
import com.bingzo.bullet.common.core.constant.SecurityConstants;
import com.bingzo.bullet.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * 用户详细信息
 *
 * @author bingzo
 */
@Slf4j
@Service
@AllArgsConstructor
public class BulletUserDetailsServiceImpl implements BulletUserDetailsService {
    private final CacheManager cacheManager;
    private final UserService<R> userService;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException,SQLException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (BulletUser) cache.get(username).get();
        }
        /*UserInfo result = new UserInfo();
        try {
            StringBuilder sql = new StringBuilder("select user_id userId,username,password,dept_id deptId,tenant_id tenantId,lock_flag lockFlag from sys_user where username='"+username+"'");
            List<SysUser> sysUsers = Db.use(dataSource).query(sql.toString(), SysUser.class);
            result.setSysUser(sysUsers.get(0));
            sql.delete(0, sql.length());
            sql.append("SELECT r.role_id roleId from sys_role r, sys_user_role ur WHERE r.role_id = ur.role_id AND r.del_flag = 0 and  ur.user_id IN(" + sysUsers.get(0).getUserId() + ")");
            List<SysRole> roles = Db.use(dataSource).query(sql.toString(), SysRole.class);
            Integer[] roleIdArr = new Integer[roles.size()];
            for (int i = 0; i < roles.size() ; i++) {
                Integer roleId = roles.get(i).getRoleId();
                roleIdArr[i] = roleId;
            }
            result.setRoles(roleIdArr);
//            result.setRoles(ArrayUtil.toArray(roles, Integer.class));
            Set<String> permissions = new HashSet<>();
            for (int i = 0; i < roleIdArr.length ; i++) {
                sql.delete(0, sql.length());
                sql.append("SELECT sys_menu.permission FROM sys_menu, sys_role_menu where sys_menu.menu_id = sys_role_menu.menu_id and sys_menu.permission is not null and sys_menu.permission !='' and sys_menu.del_flag = 0 AND sys_role_menu.role_id = " + roleIdArr[i] + " ORDER BY sys_menu.sort DESC");
                try {
                    List<String> permissionList = Db.use(dataSource).query(sql.toString(), String.class);
                    permissions.addAll(permissionList);
                } catch (SQLException e) {

                }
            }
//            roleIdArr.forEach(roleId1 -> {
//                sql.delete(0, sql.length());
//                sql.append("SELECT sys_menu.permission FROM sys_menu, sys_role_menu where sys_menu.menu_id = sys_role_menu.menu_id and sys_menu.permission is not null and sys_menu.permission !='' and sys_menu.del_flag = 0 AND sys_role_menu.role_id = " + roleId + " ORDER BY sys_menu.sort DESC");
//                try {
//                    List<String> permissionList = Db.use(dataSource).query(sql.toString(), String.class);
//                    permissions.addAll(permissionList);
//                } catch (SQLException e) {
//
//                }
//            });
            result.setPermissions(ArrayUtil.toArray(permissions, String.class));
        } catch (SQLException e) {

        }*/
        R<UserInfo> result=userService.loadUserByUsername(username);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }


    /**
     * 根据社交登录code 登录
     *
     * @param inStr TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(String inStr) {
//        return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
        return null;
    }


    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(R<UserInfo> result) {
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

       UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
        // 构造security用户
        return new BulletUser(user.getUserId(), user.getDeptId(), user.getTenantId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
                true, true, !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
    }
}
