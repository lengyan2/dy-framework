package com.dy.framework.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 主键id生成器策略枚举类
 * @author daiyuanjing
 * @date 2023-07-18 22:47
 */
@AllArgsConstructor
@Getter
public enum IdGeneratorStrategyEnum implements DyBaseEnum<Integer>{

    /**
     * Twitter雪花算法
     */
    SNOWFLAKE(1,"Twitter SnowFlake"),

    /**
     * Mybatis Plus 算法
     */
    SEQUENCE(2,"Mybatis-plus Sequence");

    private final Integer value;

    private final String label;

}
