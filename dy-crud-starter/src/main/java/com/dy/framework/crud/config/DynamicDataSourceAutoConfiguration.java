package com.dy.framework.crud.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.HikariDataSourceCreator;
import com.dy.framework.crud.dynamicdatasource.DataSourceDefinitionProvider;
import com.dy.framework.crud.dynamicdatasource.DynamicDataSourceRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * 动态数据源自动配置类
 *
 * @author daiyuanjing
 * @date 2023-07-19 20:23
 */

@ConditionalOnClass(value = DynamicRoutingDataSource.class)
public class DynamicDataSourceAutoConfiguration {

    @Bean
    public DynamicDataSourceRegistry dynamicDataSourceRegistry(
            DynamicRoutingDataSource dynamicRoutingDataSource,
            HikariDataSourceCreator dataSourceCreator,
            ObjectProvider<DataSourceDefinitionProvider> dataSourceDefinitionProviders

    ) {
        return new DynamicDataSourceRegistry(dynamicRoutingDataSource, dataSourceCreator, dataSourceDefinitionProviders);
    }

}
