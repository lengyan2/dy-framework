package com.dy.framework.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * 租户上下文持有者类
 * @author daiyuanjing
 * @Date 2023-07-19
 */
@UtilityClass
public class TenantContextHolder {

    private final TransmittableThreadLocal<TenantContext> THREAD_LOCAL_CONTENT = new TransmittableThreadLocal<>();


    /**
     * 获取当前租户的上下文
     * @returen null or 当前租户上下文
     */
    public TenantContext getTenantContext() {
        return THREAD_LOCAL_CONTENT.get();
    }

    /**
     * 获取当前租户上下文
     */

    public Optional<TenantContext> getTenantContextOptional(){
        return Optional.ofNullable(THREAD_LOCAL_CONTENT.get());
    }

    /**
     * 设置当前租户的上下文
     * @Param 租户上下文 ，传null 则清除
     */
    public synchronized void setTenantContext(TenantContext tenantContext){
        if (tenantContext == null){
            THREAD_LOCAL_CONTENT.remove();
            return;
        }
        THREAD_LOCAL_CONTENT.set(tenantContext);

    }

    /**
     * 清空本线程租户上下文，防止影响被线程复用的其他线程，以及内存泄漏
     */
    public void clear(){
        setTenantContext(null);
    }

    /**
     * 获取当前租户id
     */
    public Long getTenantId(){
        TenantContext tenantContext = getTenantContext();
        return tenantContext == null ? null : tenantContext.getTenantId();
    }

    /**
     * 获取当前租户名称
     */
    public String getTenantName(){
        TenantContext tenantContext = getTenantContext();
        return tenantContext == null ? null : tenantContext.getTenantName();
    }
}
