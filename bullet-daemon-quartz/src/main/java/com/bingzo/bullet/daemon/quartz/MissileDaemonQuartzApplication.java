package com.bingzo.bullet.daemon.quartz;

import com.bingzo.bullet.common.swagger.annotation.EnableBulletSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author
 * @date 2019/01/23
 * 定时任务模块
 */
@EnableBulletSwagger2
@SpringCloudApplication
public class MissileDaemonQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissileDaemonQuartzApplication.class, args);
	}
}
