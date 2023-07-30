package com.dy.framework.rocketmq.config;

import com.dy.framework.rocketmq.aspect.StreamListenerPrintAspect;
import org.springframework.context.annotation.Bean;

/**
 * rocketmq自动装配
 *
 * @author daiyuanjing
 * @date 2023-07-30 15:31
 */

public class DyRocketMqAutoConfiguration {

    @Bean
    public StreamListenerPrintAspect streamListenerPrintAspect (){
        return new StreamListenerPrintAspect();
    }
}
