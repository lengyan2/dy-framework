package com.dy.framework.rocketmq.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * rocketmq 配置项
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Data
@ConfigurationProperties(prefix = "dy.rocketmq",ignoreInvalidFields = true)
public class RocketMqProperties {

    /**
     * 设置nameSevAddr
     */
    private String nameSrvAddr;

    /**
     * 设置消息发送的超时时间，单位(毫秒) 默认3000
     */
    private Integer sendMsgTimeOutMillis = 3000;

    /**
     * 设置事务消息第一次回查的最快时间，单位(秒)
     */
    private Integer checkImmunityTimeInSeconds = 5;

    /**
     * rocketmq 消费者 线程数
     */
    private Integer consumeThreadNums = Runtime.getRuntime().availableProcessors() * 2 +1;

    /**
     * 设置消息消费失败的最大重试次数 默认：16
     */
    private Integer maxReconsumeTimes = 16;

    /**
     * 设置每条消息消费的最大超时时间，超过设置时间则被视为消费失败，等下次重新投递再次消费 需自己判断业务时间，默认15分钟
     */
    private Integer consumeTimeOut = 15;

    /**
     * 适用于顺序消息，设置消息消费失败的重试间隔时间 默认100毫秒
     */
    private  Integer suspendTimeMillis = 100;

    /**
     * 异步发送消息执行callback的线程池线程数
     */
    private Integer callBackThreadNums = Runtime.getRuntime().availableProcessors() * 2 +1;

    /**
     * 初始化消费者线程数 ，尽量于消费者数量一致 默认 cpu*2 + 1;
     */
    private Integer createConsumeThreadNums = Runtime.getRuntime().availableProcessors() * 2 +1;

    /**
     * 初始化生产者线程数 (尽量于生产者数量一致) 默认cpu*2 +1 ;
     */
    private Integer createProducerThreadNums = Runtime.getRuntime().availableProcessors()*2 +1;

    /**
     * 生产者发送消息线程数  默认 cpu * 2 + 1
     */
    private Integer sendMessageThreadNums = Runtime.getRuntime().availableProcessors()* 2 + 1;


}
