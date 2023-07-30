package com.dy.framework.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Web请求响应日志切面
 *
 * @author daiyuanjing
 * @date 2023-07-30 16:49
 */
@Aspect
@Slf4j
@AutoConfiguration
public class WebLogAspect {


    /**
     * Pointcut 匹配service controller
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void  restControllerPointcut(){

    }

    /**
     * 切点匹配所有spring bean
     */
    @Pointcut("within(com.dy.framework..*) || within(com.dy.module..*)")
    public void applicationPackagePointcut(){
    }

    /**
     * 日志记录请求体及响应体
     * 切面一定程度上来说有性能损失，影响qps，不建议在生产上使用本切面类
     *
     * @Param point 织入点
     *
     */
    @Around("restControllerPointcut() && applicationPackagePointcut()")
    public Object restControllerAround(ProceedingJoinPoint point) throws Throwable{
        RequestAttributes requestAttributes =  RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        /**
         * 请求体记录
         */
        StringBuilder reqLog = new StringBuilder(500);
        List<Object> reqLogParameters = new ArrayList<>();

        reqLog.append("\n\n ==================== Request Start ==================== \n");

        // 打印路由 POST: /api/v1/doSth
        reqLog.append("{}: {} \n");
        reqLogParameters.add(request.getMethod());
        reqLogParameters.add(request.getRequestURI());


        // 打印入参
        reqLog.append("Parameters: {} \n");
        reqLogParameters.add(Arrays.stream(point.getArgs()).map(Object::toString).collect(Collectors.joining("\n")));
        reqLog.append("==================== Request End ========================\n");
        log.debug(reqLog.toString(),reqLogParameters.toArray());


        /**
         * 响应体记录
         */
        StringBuilder repLog = new StringBuilder(500);
        List<Object> repLogParameters = new ArrayList<>();

        long beginTime = System.nanoTime();
        repLog.append("\n\n================  Response Start  ================\n");
        try {
            Object result = point.proceed();
            repLog.append("result: {}\n");
            repLogParameters.add(result);
            return result;
        }catch (IllegalArgumentException e){
            log.error("Illegal argument: {} in {}.{}",Arrays.toString(point.getArgs()),
                    point.getSignature().getDeclaringTypeName(),point.getSignature().getName()
                    );
            throw e;
        }finally {
            long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - beginTime);
            repLog.append("{}: {} ( {} ms) \n");
            repLogParameters.add(request.getMethod());
            repLogParameters.add(request.getRequestURI());
            repLogParameters.add(duration);
            repLog.append("================   Response End   ================\n");

            log.debug(repLog.toString(),reqLogParameters.toArray());
        }
    }
}
