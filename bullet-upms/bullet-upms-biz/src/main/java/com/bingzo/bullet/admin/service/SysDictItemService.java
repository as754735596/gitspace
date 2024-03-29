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

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingzo.bullet.admin.api.entity.SysDictItem;
import com.bingzo.bullet.common.core.util.R;

/**
 * 字典项
 *
 * @author bingzo
 * @date 2019/03/19
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 *
	 * @param id 字典项ID
	 * @return
	 */
	R removeDictItem(Integer id);

	/**
	 * 更新字典项
	 *
	 * @param item 字典项
	 * @return
	 */
	R updateDictItem(SysDictItem item);
}
