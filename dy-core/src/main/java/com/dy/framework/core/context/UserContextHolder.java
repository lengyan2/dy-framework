package com.dy.framework.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * 用户上下文持有者类
 * @author daiyuanjing
 * @Date 2023-07-19
 */
@UtilityClass
public class UserContextHolder {

    private final TransmittableThreadLocal<UserContext> THREAD_LOCAL_CONTEXT = new TransmittableThreadLocal<>();


    /**
     * 获取当前用户上下文
     * @return null or 当前用户上下文
     */
    public UserContext getUserContext()
    {
        return THREAD_LOCAL_CONTEXT.get();
    }

    /**
     * 获取当前用户上下文
     * @return
     */
    public Optional<UserContext> getUserContextOptional(){
        return Optional.ofNullable(THREAD_LOCAL_CONTEXT.get());
    }

    /**
     * 设置当前用户上下文
     * @param userContext 用户上下文对象 传null则清除threadLocal
     */
    public synchronized void setUserContext(UserContext userContext){
        if (userContext == null){
            THREAD_LOCAL_CONTEXT.remove();
            return;
        }
        THREAD_LOCAL_CONTEXT.set(userContext);
    }

    /**
     * 强制清空本线程用户上下文，防止影响被线程池复用的其他线程，以防内存泄漏
     */
    public void clear(){
        setUserContext(null);
    }

    /**
     * 获取当前用户id
     * @return
     */
    public Long getUserId(){
        UserContext userContext = getUserContext();
        return userContext == null ? null : userContext.getUserId();
    }

    /**
     * 获取当前用户名称
     * @return
     */
    public String getUserName(){
        UserContext userContext = getUserContext();
        return userContext == null ? null : userContext.getUserName();
    }

    /**
     * 获取用户手机号码
     * @return
     */
    public String getUserPhoneNo(){
        UserContext userContext = getUserContext();
        return userContext == null ? null : userContext.getUserPhoneNo();
    }

    /**
     * 获取客户端Ip
     * @return
     */
    public String getClientIp(){
        UserContext userContext = getUserContext();
        return userContext == null ? null : userContext.getClientIp();
    }
}
