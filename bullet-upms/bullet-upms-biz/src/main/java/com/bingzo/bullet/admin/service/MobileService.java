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

package com.bingzo.bullet.admin.service;

import com.bingzo.bullet.common.core.util.R;

/**
 * @author bingzo
 * @date 2018/11/14
 */
public interface MobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);
}
