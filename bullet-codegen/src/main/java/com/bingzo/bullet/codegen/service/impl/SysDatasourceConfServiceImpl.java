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
package com.bingzo.bullet.codegen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingzo.bullet.codegen.entity.SysDatasourceConf;
import com.bingzo.bullet.codegen.mapper.SysDatasourceConfMapper;
import com.bingzo.bullet.codegen.service.SysDatasourceConfService;
import com.bingzo.bullet.common.datasource.config.DynamicDataSourceConfig;
import lombok.AllArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

/**
 * 数据源表
 *
 * @author missile code generator
 * @date 2019-03-31 16:00:20
 */
@Service
@AllArgsConstructor
//@EnableDynamicDataSource
public class SysDatasourceConfServiceImpl extends ServiceImpl<SysDatasourceConfMapper, SysDatasourceConf> implements SysDatasourceConfService {
	private final DynamicDataSourceConfig dynamicDataSourceConfig;
	private final StringEncryptor stringEncryptor;

	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDatasourceConf
	 * @return
	 */
	@Override
	public Boolean saveDsByEnc(SysDatasourceConf sysDatasourceConf) {
		sysDatasourceConf.setPassword(stringEncryptor.encrypt(sysDatasourceConf.getPassword()));
		this.baseMapper.insert(sysDatasourceConf);
		return dynamicDataSourceConfig.reload();
	}

	/**
	 * 更新数据源
	 *
	 * @param sysDatasourceConf
	 * @return
	 */
	@Override
	public Boolean updateDsByEnc(SysDatasourceConf sysDatasourceConf) {
		if (StrUtil.isNotBlank(sysDatasourceConf.getPassword())) {
			sysDatasourceConf.setPassword(stringEncryptor.encrypt(sysDatasourceConf.getPassword()));
		}
		this.baseMapper.updateById(sysDatasourceConf);
		return dynamicDataSourceConfig.reload();
	}
}
