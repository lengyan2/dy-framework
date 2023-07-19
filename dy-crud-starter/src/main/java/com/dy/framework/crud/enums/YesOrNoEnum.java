package com.dy.framework.crud.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.dy.framework.core.enums.DyBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是或否枚举
 * @author daiyuanjing
 * @date 2023-07-19 23:42
 */
@AllArgsConstructor
@Getter
public enum YesOrNoEnum implements DyBaseEnum<Integer> {

    YES(1,"是"),
    NO(0,"否");

    @EnumValue
    private Integer value;

    private String label;

    /**
     * 根据值获取枚举对象
     * @param value
     * @return
     */
    public static YesOrNoEnum of(Integer value){
        if (value == null){return null;}
        if (YES.getValue().equals(value)){
            return YES;
        }
        if (NO.getValue().equals(value)){
            return NO;
        }
        return null;
    }

    /**
     * 取反值
     */

    public static YesOrNoEnum reverse(YesOrNoEnum old){
        if (old == null){
            return null;
        }
        switch (old){
            case NO:
                return YES;
            case YES:
                return NO;
        }
        throw new IllegalArgumentException();
    }
}
