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
package com.bingzo.bullet.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingzo.bullet.admin.api.entity.SysDict;
import com.bingzo.bullet.admin.api.entity.SysDictItem;
import com.bingzo.bullet.admin.mapper.SysDictItemMapper;
import com.bingzo.bullet.admin.service.SysDictItemService;
import com.bingzo.bullet.admin.service.SysDictService;
import com.bingzo.bullet.common.core.constant.CacheConstants;
import com.bingzo.bullet.common.core.constant.enums.DictTypeEnum;
import com.bingzo.bullet.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 字典项
 *
 * @author bingzo
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {
	private final SysDictService dictService;

	/**
	 * 删除字典项
	 *
	 * @param id 字典项ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R removeDictItem(Integer id) {
		//根据ID查询字典ID
		SysDictItem dictItem = this.getById(id);
		SysDict dict = dictService.getById(dictItem.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return R.failed("系统内置字典项目不能删除");
		}
		return R.ok(this.removeById(id));
	}

	/**
	 * 更新字典项
	 *
	 * @param item 字典项
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R updateDictItem(SysDictItem item) {
		//查询字典
		SysDict dict = dictService.getById(item.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return R.failed("系统内置字典项目不能删除");
		}
		return R.ok(this.updateById(item));
	}
}
