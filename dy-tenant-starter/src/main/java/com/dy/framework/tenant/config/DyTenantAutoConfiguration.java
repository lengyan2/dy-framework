package com.dy.framework.tenant.config;

import com.dy.framework.core.props.DyProperties;
import com.dy.framework.crud.support.TenantSupport;
import com.dy.framework.crud.support.impl.DefaultTenantSupport;
import com.dy.framework.tenant.support.TenantLineSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 多租户自动配置
 * @author daiyuanjing
 * @date 2023-07-20
 */
@AutoConfiguration
@RequiredArgsConstructor
public class DyTenantAutoConfiguration {

    private final DyProperties dyProperties;

    @Bean
    public TenantSupport tenantSupport(){
        if (!dyProperties.getTenant().getEnabled().equals(Boolean.TRUE)){
            // 引入了 starter，但未启用多租户
            return new DefaultTenantSupport();
        }
        switch (dyProperties.getTenant().getIsolateLevel()){
            case LINE:
                return new TenantLineSupport();
            default:
                throw new IllegalArgumentException("目前只支持行级多租户配置");
        }
    }
}
