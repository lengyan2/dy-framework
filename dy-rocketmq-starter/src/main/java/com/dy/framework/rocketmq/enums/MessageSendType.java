package com.dy.framework.rocketmq.enums;

/**
 *  消息发送类型
 * @author daiyuanjing
 * @date 2023-07-21
 */
public enum MessageSendType {

    /**
     * 同步发送
     */
    SEND,

    /**
     * 异步发送
     */
    SEND_ASYNC,

    /**
     * 单向发送
     */
    SEND_ONE_WAY
}
