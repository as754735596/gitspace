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

package com.bingzo.bullet.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.bingzo.common.minio.service.MinioTemplate;
import com.bingzo.bullet.common.core.constant.CommonConstants;
import com.bingzo.bullet.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bingzo
 * @date 2018-12-30
 * <p>
 * 文件管理
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/file")
@Api(value = "file", tags = "文件管理模块")
public class FileController {
	private final MinioTemplate minioTemplate;

	/**
	 * 上传文件
	 * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 *
	 * @param file 资源
	 * @return R(bucketName, filename)
	 */
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);

		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}

	/**
	 * 获取文件
	 *
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */
	@GetMapping("/{fileName}")
	public void file(@PathVariable String fileName, HttpServletResponse response) {
		Integer separator = fileName.lastIndexOf(StrUtil.DASHED);
		try (InputStream inputStream = minioTemplate.getObject(fileName.substring(0, separator), fileName.substring(separator + 1, fileName.length()))) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}
}
