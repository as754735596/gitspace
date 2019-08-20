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
package com.bingzo.bullet.codegen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingzo.bullet.codegen.entity.SysDatasourceConf;

/**
 * 数据源表
 *
 * @author missile code generator
 * @date 2019-03-31 16:00:20
 */
public interface SysDatasourceConfService extends IService<SysDatasourceConf> {
	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDatasourceConf
	 * @return
	 */
	Boolean saveDsByEnc(SysDatasourceConf sysDatasourceConf);

	/**
	 * 更新数据源
	 *
	 * @param sysDatasourceConf
	 * @return
	 */
	Boolean updateDsByEnc(SysDatasourceConf sysDatasourceConf);
}