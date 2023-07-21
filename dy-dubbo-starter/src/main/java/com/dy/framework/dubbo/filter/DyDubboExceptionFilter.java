package com.dy.framework.dubbo.filter;

import cn.hutool.core.util.StrUtil;
import com.dy.framework.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.filter.ExceptionFilter;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * 自定义dubbo异常处理
 *
 * @auther daiyuanjing
 * @date 2023-07-21
 */
@Slf4j
public class DyDubboExceptionFilter extends ExceptionFilter {

    private static final String BUSINESS_EXCEPTION_CLASS_NAME = BusinessException.class.getSimpleName();

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (!(appResponse.hasException() && GenericService.class != invoker.getInterface())) {
            return;
        }
        try {
            Throwable exception = appResponse.getException();

            if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                return;
            }

            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (Class<?> exceptionType : exceptionTypes) {
                if (exception.getClass().equals(exceptionType)) {
                    return;
                }
            }
            log.error(
                    "Got unchecked and undeclared exception which called by {} . service {} ,method {}  , exception {} >> {}",
                    RpcContext.getServerContext().getRemoteAddress(),
                    invoker.getInterface().getName(),
                    invocation.getMethodName(),
                    exception.getClass().getName(),
                    exception.getMessage(),
                    exception
            );
            String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
            String exceptionFIle = ReflectUtils.getCodeBase(exception.getClass());
            if (serviceFile == null || exceptionFIle == null || serviceFile.equals(exceptionFIle)) {
                return;
            }

            String className = exception.getClass().getName();
            if (className.startsWith("java.") || className.startsWith("javax.")) {
                return;
            }

            if (exception instanceof RpcException) {
                return;
            }
            // 业务异常
            if (className.contains(BUSINESS_EXCEPTION_CLASS_NAME)) {
                return;
            }
            appResponse.setException(new RuntimeException(StrUtil.toString(exception)));
        } catch (NoSuchMethodException cause) {

        } catch (Throwable cause) {
            log.warn("Fail to ExceptionFilter when called by {}, service: {} , method: {} , exception: {} >> {}",
                    RpcContext.getServerContext().getRemoteHost(),
                    invoker.getInterface().getName(),
                    invocation.getMethodName(),
                    cause.getClass().getName(),
                    cause.getMessage(),
                    cause);
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        log.error(
                "Got unchecked and undeclared exception which called by {} ,service:{}, method:{} ,exception: {} >> {}",
                RpcContext.getServerContext().getRemoteHost(),
                invoker.getInterface().getName(),
                invocation.getMethodName(),
                e.getClass().getName(),
                e.getMessage(),
                e
        );
    }
}
