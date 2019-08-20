package com.bingzo.bullet.daemon.quartz.task;

import com.bingzo.bullet.daemon.quartz.constant.enums.MissileQuartzEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("testTask")
public class TestTask {
    int count=0;

    public String testTask(){

        count++;
        System.out.println("定时任务二执行---  执行次数="+count);
        return MissileQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType();
    }

}
