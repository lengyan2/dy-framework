package com.dy.framework.crud.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * 动态数据源自动配置类
 * @author daiyuanjing
 * @date 2023-07-19 20:23
 */

@ConditionalOnClass(value = DynamicRoutingDataSource.class)
public class DynamicDataSourceAutoConfiguration {


}
