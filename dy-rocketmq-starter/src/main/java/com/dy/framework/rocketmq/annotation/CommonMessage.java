package com.dy.framework.rocketmq.annotation;

import com.dy.framework.rocketmq.core.producer.DefaultSendCallback;
import com.dy.framework.rocketmq.enums.MessageSendType;
import org.apache.rocketmq.client.producer.SendCallback;

import java.lang.annotation.*;

/**
 * 普通消息注解
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonMessage {

    /**
     * 消息所属主题 topic
     * @return String
     */
    String topic() default "";

    /**
     * 订阅主题下的tags
     * * 订阅所有消息
     * taga 订阅tagA下的消息
     * @return String
     */
    String tag() default "*";

    /**
     * 消息发送类型 默认异步
     * @return MessageSendType
     */
    MessageSendType messageSendType() default MessageSendType.SEND_ASYNC;


    /**
     * 自定义SendCallBack
     * @return callback
     */
    Class< ? extends SendCallback> callback() default DefaultSendCallback.class;
}
