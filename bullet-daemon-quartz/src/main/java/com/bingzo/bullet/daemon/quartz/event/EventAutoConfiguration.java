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

package com.bingzo.bullet.daemon.quartz.event;

import com.bingzo.bullet.daemon.quartz.config.MissileQuartzInvokeFactory;
import com.bingzo.bullet.daemon.quartz.service.SysJobLogService;
import com.bingzo.bullet.daemon.quartz.util.TaskInvokUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author
 * @date 2018/6/28
 * <p>
 * 多线程自动配置
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {
	@Autowired
	private TaskInvokUtil taskInvokUtil;
	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokUtil);
	}

	@Bean
	public MissileQuartzInvokeFactory missileQuartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new MissileQuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokUtil taskInvokUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokUtil(publisher);
	}

}
