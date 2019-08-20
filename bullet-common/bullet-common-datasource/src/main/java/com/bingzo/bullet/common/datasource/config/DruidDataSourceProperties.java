package com.bingzo.bullet.common.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author bingzo
 * @date 2019-05-14
 * <p>
 * 参考DruidDataSourceWrapper
 */
@Data
@Component
@Primary
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {
	private String username;
	private String password;
	private String url;
	private String driverClassName;
	private String filters;
}
