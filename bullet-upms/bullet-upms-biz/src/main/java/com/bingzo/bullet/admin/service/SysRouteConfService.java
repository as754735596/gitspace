///*
// *    Copyright (c) 2018-2025, bingzo All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *
// * Redistributions of source code must retain the above copyright notice,
// * this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
// * notice, this list of conditions and the following disclaimer in the
// * documentation and/or other materials provided with the distribution.
// * Neither the name of the bingzo.com developer nor the names of its
// * contributors may be used to endorse or promote products derived from
// * this software without specific prior written permission.
// * Author: bingzo (service@bingzo.cn)
// */
//
//package com.bingzo.missile.admin.service;
//
//import cn.hutool.json.JSONArray;
//import com.baomidou.mybatisplus.extension.service.IService;
//import SysRouteConf;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
///**
// * 路由
// *
// * @author bingzo
// * @date 2018-11-06 10:17:18
// */
//public interface SysRouteConfService extends IService<SysRouteConf> {
//
//	/**
//	 * 获取全部路由
//	 * <p>
//	 * RedisRouteDefinitionWriter.java
//	 * PropertiesRouteDefinitionLocator.java
//	 *
//	 * @return
//	 */
//	List<SysRouteConf> routes();
//
//	/**
//	 * 更新路由信息
//	 *
//	 * @param routes 路由信息
//	 * @return
//	 */
//	Mono<Void> updateRoutes(JSONArray routes);
//}
//
