package com.bingzo.bullet.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bingzo.bullet.admin.api.dto.UserInfo;
import com.bingzo.bullet.admin.api.entity.SysRole;
import com.bingzo.bullet.admin.api.entity.SysUser;
import com.bingzo.bullet.admin.api.vo.MenuVO;
import com.bingzo.bullet.admin.service.*;
import com.bingzo.bullet.common.core.util.R;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final SysUserService sysUserService;
    private final SysMenuService sysMenuService;
    private final SysRoleService sysRoleService;

    @Override
    public R loadUserByUsername(String username) {
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.failed(null, String.format("用户信息为空 %s", username));
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表  （ID）
        List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
                .stream()
                .map(SysRole::getRoleId)
                .collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
                    .stream()
                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                    .map(MenuVO::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return R.ok(userInfo);
    }
}
