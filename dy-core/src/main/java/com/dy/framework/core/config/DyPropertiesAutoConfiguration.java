package com.dy.framework.core.config;

import com.dy.framework.core.props.DyProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * properties 解析自动配置类
 *
 * @author daiyuanjing
 * @date 2023-07-18 21:41
 */
@EnableConfigurationProperties(value = {DyProperties.class})
@AutoConfiguration
public class DyPropertiesAutoConfiguration {
}
