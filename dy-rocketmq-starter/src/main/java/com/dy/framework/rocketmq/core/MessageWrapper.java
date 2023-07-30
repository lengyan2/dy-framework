package com.dy.framework.rocketmq.core;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * 消息体包装器
 *
 * @author daiyuanjing
 * @date 2023-07-29 9:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息发送keys
     */
    @NotNull
    private String keys;

    /**
     * 消息体
     */
    @NotNull
    private T message;

    /**
     * 唯一标识 用于验证客户端幂等验证
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * 消息发送时间
     */
    private Long timestamp = System.currentTimeMillis();
}
