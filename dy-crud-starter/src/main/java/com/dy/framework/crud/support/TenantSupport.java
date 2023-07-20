package com.dy.framework.crud.support;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.dy.framework.core.props.DyProperties;

/**
 * 多租户支持
 *
 * @author daiyuanjing
 * @date 2023-07-20
 */
public interface TenantSupport {


    /**
     * 不同的多租户隔离级别分别实现本方法，按需添加sql拦截器
     * @param dyProperties
     * @param interceptor
     */
    void support(DyProperties dyProperties, MybatisPlusInterceptor interceptor);
}
