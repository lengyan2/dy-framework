package com.dy.framework.rocketmq.core.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * 发送消息 回调
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Slf4j

public class DefaultSendCallback implements SendCallback {
    /**
     * 发送成功回调方法
     * @param sendResult 发送结果
     */
    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("Message sent success , << SendResult >> = {} ",sendResult);
    }

    /**
     * 消息发送失败回调方法
     * @param throwable 失败异常
     */
    @Override
    public void onException(Throwable throwable) {
        log.error("Failed to send Message , exception = {}",throwable.getMessage());
    }
}
