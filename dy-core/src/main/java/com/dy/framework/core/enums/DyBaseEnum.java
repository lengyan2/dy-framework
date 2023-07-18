package com.dy.framework.core.enums;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dy.framework.core.exception.BusinessException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 基础枚举类
 * @author daiyuanjing
 * @date 2023-07-18 21:47
 */
public interface DyBaseEnum<T> extends Serializable {


    /**
     * 从指定的枚举类中查找想要的枚举 ，并返回一个OPtional 如果没找到则返回optional.empty()
     * @param type 实现了DyBaseEnum的枚举类
     * @param predicate 判断逻辑
     * @return 返回结果
     * @param <T> 枚举类型
     */
    static <T extends Enum<?> & DyBaseEnum<?>> Optional<T> find (Class<T> type, Predicate<T> predicate){
        if (type.isEnum()){
            for (T each : type.getEnumConstants()) {
                if (predicate.test(each)){
                    return Optional.of(each);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 根据枚举 DyBaseEnum getValue 查找枚举
     * @param type 实现了DyBaseEnum的枚举类
     * @param value 枚举值
     * @return 返回Optional
     * @param <T> 枚举类型
     */
    static  <T extends Enum<?> & DyBaseEnum<?>> Optional<T> findByValue(Class<T> type,Object value){
        return find(type,e -> e.getValue() == value || e.getValue().equals(value)
                || String.valueOf(e.getValue()).equalsIgnoreCase(String.valueOf(value)));
    }

    /**
     * 根据枚举的label来查找枚举
     * @param type 实现了DyBaseEnum的枚举类
     * @param text 枚举标签
     * @return 返回Optional
     * @param <T> 枚举类型
     */
    static <T extends Enum<?> & DyBaseEnum<?>> Optional<T> findByLabel(Class<T> type,String text){
        return find(type,e -> e.getLabel().equalsIgnoreCase(text));
    }

    static <T extends  Enum<?> & DyBaseEnum<?>> Optional<T> find(Class<T> type,Object target){
        return find(type,e -> e.eq(target));
    }

    static <E extends DyBaseEnum<?>>Optional<E> of(Class<E> type,Object value){
        if (type.isEnum()){
            for (E enumConstant : type.getEnumConstants()) {
                Predicate<E> predicate = e -> e.getValue() == value || e.getValue().equals(value)
                        || String.valueOf(e.getValue()).equalsIgnoreCase(String.valueOf(value));
                if (predicate.test(enumConstant)){
                    return Optional.of(enumConstant);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 对比v是否和getvalue相等 ， 比较值 比较地址 比较label
     * @param v value
     * @return 是否相等
     */
    default boolean eq(Object v){
        if (v == null){
            return false;
        }
        if (v instanceof Object[]){
            v = Arrays.asList(v);
        }
        return this == v || getValue() == v || getValue().equals(v)
                || String.valueOf(getValue()).equalsIgnoreCase(String.valueOf(v))
                || getLabel().equalsIgnoreCase(String.valueOf(v));
    }



    /**
     * 枚举选项的值，通常由字母或数字组成，并且在一同一个枚举中唯一，对应数据库中的值也是为此值
     * @return 枚举的值
     */
    T getValue();

    /**
     * 枚举选项的文本
     * @return 枚举的文本
     */
    String getLabel();


    /**
     * 将不定类型的value转换为int
     *
     * @return int value
     */
    default int convertValue2Int(){
        if (this.getValue() == null){
            throw new IllegalArgumentException("枚举类的value不能为null ");
        }
        if (this.getValue() instanceof Integer){
            return (Integer) getValue();
        }
        if (this.getValue() instanceof  Long){
            return ((Long) getValue()).intValue();
        }
        if (this.getValue() instanceof Number){
            return ((Number)getValue()).intValue();
        }
        if (this.getValue() instanceof String){
            return NumberUtil.toBigDecimal((String) getValue()).intValue();
        }
        throw new IllegalArgumentException("枚举类的value 不能自动转换为int");
    }

    /**
     * 格式label
     * @param templateParams
     * @return
     */
    default String formatLabel(Object... templateParams){
        if (ArrayUtil.isEmpty(templateParams)){
            return this.getLabel();
        }
        return StrUtil.format(this.getLabel(),templateParams);
    }

    /**
     * 断言不为null
     * 枚举选项的value 建议为整数类型
     *
     * @param object 需要判断的对象
     * @param templateParams label中如果由占位符，向里面填充模板参数
     */
    default void assertNotNull(Object object,Object... templateParams){
        if (ObjectUtil.isNotNull(object)){
            return;
        }
        throw new BusinessException(this.convertValue2Int(),this.formatLabel(templateParams));
    }

    /**
     * 断言不为空文本
     * @param cs 需要判断的对象
     * @param templateParams label中如果有占位符，向里面填充模板参数
     */
    default void assertNotBlank(CharSequence cs,Object... templateParams){
        if (StrUtil.isNotBlank(cs)){
            return;
        }
        throw new BusinessException(this.convertValue2Int(),this.formatLabel(templateParams));
    }

    /**
     * 断言不为空集合
     * @param iterable 迭代器对象
     * @param templateParams label中如果有占位符,向里面填充模板参数
     */
    default void assertNotEmpty(Iterable<?> iterable,Object... templateParams){
        if (IterUtil.isNotEmpty(iterable)){
            return;
        }
        throw new BusinessException(this.convertValue2Int(),this.formatLabel(templateParams));
    }
}
