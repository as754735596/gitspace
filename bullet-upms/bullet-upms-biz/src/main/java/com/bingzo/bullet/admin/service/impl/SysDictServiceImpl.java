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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingzo.bullet.admin.api.entity.SysDict;
import com.bingzo.bullet.admin.api.entity.SysDictItem;
import com.bingzo.bullet.admin.mapper.SysDictItemMapper;
import com.bingzo.bullet.admin.mapper.SysDictMapper;
import com.bingzo.bullet.admin.service.SysDictService;
import com.bingzo.bullet.common.core.constant.CacheConstants;
import com.bingzo.bullet.common.core.constant.enums.DictTypeEnum;
import com.bingzo.bullet.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典表
 *
 * @author bingzo
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 *
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public R removeDict(Integer id) {
		SysDict dict = this.getById(id);
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return R.failed("系统内置字典不能删除");
		}

		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery()
				.eq(SysDictItem::getDictId, id));
		return R.ok();
	}

	/**
	 * 更新字典
	 *
	 * @param dict 字典
	 * @return
	 */
	@Override
	public R updateDict(SysDict dict) {
		SysDict sysDict = this.getById(dict.getId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystem())) {
			return R.failed("系统内置字典不能修改");
		}
		return R.ok(this.updateById(dict));
	}
}
