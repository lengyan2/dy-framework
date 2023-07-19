package com.dy.framework.crud.dynamicdatasource;

/**
 * 数据源提供接口
 * @author daiyuanjing
 * @date 2023-07-19 20:31
 */
public interface DataSourceDefinitionProvider {

    /**
     * 根据数据源名称，得到数据源信息
     * @param dataSourceName 数据源名称
     * @return DataSourceDefinition
     */
    DataSourceDefinition getDataSourceDefinition(String dataSourceName);
}
