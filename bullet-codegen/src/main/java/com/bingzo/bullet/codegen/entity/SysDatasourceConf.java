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
package com.bingzo.bullet.codegen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据源表
 *
 * @author missile code generator
 * @date 2019-03-31 16:00:20
 */
@Data
@TableName("sys_datasource_conf")
@EqualsAndHashCode(callSuper = true)
public class SysDatasourceConf extends Model<SysDatasourceConf> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * jdbcurl
	 */
	private String url;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private LocalDateTime createDate;
	/**
	 * 更新
	 */
	private LocalDateTime updateDate;
	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;
	/**
	 * 租户ID
	 */
	private Integer tenantId;

}
