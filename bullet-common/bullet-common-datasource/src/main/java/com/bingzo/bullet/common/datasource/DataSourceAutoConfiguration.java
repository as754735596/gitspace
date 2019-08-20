package com.bingzo.bullet.common.datasource;

import com.bingzo.bullet.common.datasource.config.DruidDataSourceProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Lucky
 * @date 2019-05-18
 * <p>
 * DbAcess 自动配置类
 */
@AllArgsConstructor
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DataSourceAutoConfiguration {
}
