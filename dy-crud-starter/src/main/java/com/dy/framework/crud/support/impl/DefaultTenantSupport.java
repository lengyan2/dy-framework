package com.dy.framework.crud.support.impl;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.dy.framework.core.props.DyProperties;
import com.dy.framework.crud.support.TenantSupport;
import lombok.extern.slf4j.Slf4j;

/**
 * 多租户默认配置
 */
@Slf4j
public class DefaultTenantSupport implements TenantSupport {
    @Override
    public void support(DyProperties dyProperties, MybatisPlusInterceptor interceptor) {
        log.info("【多租户支持】>> 启用多租户，但未引入tenant-starter,无法对sql进行拦截处理");
    }
}
