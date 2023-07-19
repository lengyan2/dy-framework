package com.dy.framework.crud.dynamicdatasource;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 数据源定义
 * @author daiyuanjing
 * @date 2023-07-19 20:24
 */
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourceDefinition implements Serializable {

    private final static long serialVersionUID = 1L;

    /**
     * 数据源名称，切换数据源时需要
     */
    @ApiModelProperty(value = "数据源名称",notes = "切换数据源时需要")
    private String name;

    /**
     * 数据源驱动类名称
     */
    @ApiModelProperty(value = "数据源驱动")
    private String driverClassName;

    /**
     * 数据源连接url
     */
    @ApiModelProperty(value = "数据源连接地址")
    private String url;

    /**
     * 数据库账号
     */
    @ApiModelProperty(value = "数据库账号")
    private String username;

    /**
     * 数据库密码
     */
    @ApiModelProperty(value = "数据库密码")
    private String password;
}
