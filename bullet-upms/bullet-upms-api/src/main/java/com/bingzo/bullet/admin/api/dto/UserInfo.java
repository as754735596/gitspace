/*
 *
 *      Copyright (c) 2018-2025, bingzo All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the bingzo.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: bingzo (service@bingzo.cn)
 *
 */

package com.bingzo.bullet.admin.api.dto;

import com.bingzo.bullet.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bingzo
 * @date 2017/11/11
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;
}
