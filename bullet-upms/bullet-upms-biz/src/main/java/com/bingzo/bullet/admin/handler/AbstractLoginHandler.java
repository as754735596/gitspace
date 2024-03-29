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

package com.bingzo.bullet.admin.handler;

import com.bingzo.bullet.admin.api.dto.UserInfo;

/**
 * @author bingzo
 * @date 2018/11/18
 */
public abstract class AbstractLoginHandler implements LoginHandler {

	/***
	 * 数据合法性校验
	 * @param loginStr 通过用户传入获取唯一标识
	 * @return 默认不校验
	 */
	@Override
	public Boolean check(String loginStr) {
		return true;
	}

	/**
	 * 处理方法
	 *
	 * @param loginStr 登录参数
	 * @return
	 */
	@Override
	public UserInfo handle(String loginStr) {
		if (!check(loginStr)) {
			return null;
		}

		String identify = identify(loginStr);
		return info(identify);
	}
}
