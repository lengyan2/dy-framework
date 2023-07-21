package com.dy.framework.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * 顺序消息
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OrderMessage {

    /**
     * message 所属主题
     * @return String
     */
    String topic() default "";

    /**
     * 订阅topic下的tags
     * * 表示订阅所有消息
     * taga 表示订阅topic下tag未taga的消息
     * @return string
     */
    String tag() default "*";

}
