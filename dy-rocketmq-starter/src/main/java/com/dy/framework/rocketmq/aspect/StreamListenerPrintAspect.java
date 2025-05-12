package com.dy.framework.rocketmq.aspect;

import com.alibaba.fastjson.JSON;
import com.dy.framework.rocketmq.core.MessageWrapper;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * 日志环绕打印
 *
 * @author daiyuanjing
 * @date 2025-07-29 9:24
 */
@Aspect
public final class StreamListenerPrintAspect {


    @SneakyThrows
    @Around("@within(org.springframework.cloud.stream.annotation.StreamListener) || @annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public Object streamListenerPring(ProceedingJoinPoint joinPoint){
        Object result;
        boolean executeResult = true;
        long startTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logger logger = LoggerFactory.getLogger(methodSignature.getDeclaringTypeName());
        try {
            result = joinPoint.proceed();
        }catch (Throwable ex){
            executeResult = false;
            throw ex;
        }finally {
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length >0){
                Optional<MessageWrapper> messageWrapperOptional = Arrays.stream(args).filter(each -> each instanceof MessageWrapper)
                        .map(each -> (MessageWrapper) each)
                        .findFirst();
                if (messageWrapperOptional.isPresent()){
                    MessageWrapper messageWrapper = messageWrapperOptional.get();
                    logger.info("Execute result: {} ,Keys:{} ,Dispatch time: {}ms,Execute time:{}ms, message:{}",
                            executeResult,
                            messageWrapper.getKeys(),
                            System.currentTimeMillis() - messageWrapper.getTimestamp(),
                            System.currentTimeMillis() - startTime,
                            JSON.toJSONString(messageWrapper.getMessage()));
                }
            }
        }
        return result;
    }
}
