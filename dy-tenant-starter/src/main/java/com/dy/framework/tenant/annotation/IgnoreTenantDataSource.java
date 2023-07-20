package com.dy.framework.tenant.annotation;

import java.lang.annotation.*;

/**
 * 数据源级多租户 忽略aop拦截
 * @author daiyuanjing
 * @date 2023-07-20
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreTenantDataSource {
}
