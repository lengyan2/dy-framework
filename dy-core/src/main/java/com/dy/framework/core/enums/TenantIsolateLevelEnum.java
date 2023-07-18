package com.dy.framework.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiyuanjing
 * @date 2023-07-18 22:51
 */
@AllArgsConstructor
@Getter
public enum TenantIsolateLevelEnum implements DyBaseEnum<Integer>{

    /**
     * 行级 每张表增加一个租户id字段
     */
    LINE(1,"行级"),

    /**
     * 数据源级，每个租户独立数据库源
     */
    DATASOURCE(2,"数据源级");

    private final Integer value;

    private final String label;

}
