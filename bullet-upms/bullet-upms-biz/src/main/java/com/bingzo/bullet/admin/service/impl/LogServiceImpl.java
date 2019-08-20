package com.bingzo.bullet.admin.service.impl;

import com.bingzo.bullet.admin.api.entity.SysLog;
import com.bingzo.bullet.admin.service.SysLogService;
import com.bingzo.bullet.common.core.util.R;
import com.bingzo.bullet.common.log.service.LogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class LogServiceImpl implements LogService<SysLog> {
    private final SysLogService sysLogService;
    @Override
    public R<Boolean> saveLog(SysLog sysLog) {
        return R.ok(sysLogService.save(sysLog));
    }
}
