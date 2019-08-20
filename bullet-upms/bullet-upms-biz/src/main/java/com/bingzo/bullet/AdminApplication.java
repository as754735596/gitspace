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

package com.bingzo.bullet;


import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.bingzo.bullet.common.datasource.annotation.EnableDynamicDataSource;
import com.bingzo.bullet.common.datasource.config.DruidDataSourceProperties;
import com.bingzo.bullet.common.security.annotation.EnableBulletResourceServer;
import com.bingzo.bullet.common.security.util.SecurityUtils;
import com.bingzo.bullet.common.swagger.annotation.EnableBulletSwagger2;
import com.mysql.cj.xdevapi.SessionFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @author bingzo
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableBulletSwagger2
@SpringCloudApplication
@EnableBulletResourceServer
@ServletComponentScan
@EnableDynamicDataSource
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
