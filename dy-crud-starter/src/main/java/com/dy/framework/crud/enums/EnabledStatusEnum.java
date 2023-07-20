package com.dy.framework.crud.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.dy.framework.core.enums.DyBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用禁用枚举
 * @author daiyuanjing
 * @date 2023-07-19 23:36
 */
@AllArgsConstructor
@Getter
public enum EnabledStatusEnum implements DyBaseEnum<Integer> {

    DISABLED(0,"禁用"),

    ENABLED(1,"启用");

    @EnumValue
    private Integer value;

    private String label;

    /**
     * 根据value 获取枚举对象
     * @param value
     * @return
     */
    public static EnabledStatusEnum of(Integer value){
        if (value == null){
            return null;
        }
        if (DISABLED.getValue().equals(value)) {
            return DISABLED;
        }
        if (ENABLED.getValue().equals(value)){
            return ENABLED;
        }
        return null;
    }

    /**
     * 根据枚举取反值
     * @param old
     * @return
     */
    public static EnabledStatusEnum reverse(EnabledStatusEnum old){
        if (old == null){
            return null;
        }
        switch (old){
            case ENABLED:
                return DISABLED;
            case DISABLED:
                return ENABLED;
        }
        throw new IllegalArgumentException();
    }
}
