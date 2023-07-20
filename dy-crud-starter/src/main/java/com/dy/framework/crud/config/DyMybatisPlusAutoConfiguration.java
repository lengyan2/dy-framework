package com.dy.framework.crud.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.dy.framework.core.enums.IdGeneratorStrategyEnum;
import com.dy.framework.core.props.DyProperties;
import com.dy.framework.crud.handler.DySequenceIdGenerateHandler;
import com.dy.framework.crud.handler.DySnowFlakeIdGenerateHandler;
import com.dy.framework.crud.handler.MyBatisPlusAutoFillHandler;
import com.dy.framework.crud.support.TenantSupport;
import com.dy.framework.crud.support.impl.DefaultTenantSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus 自动配置类
 * @author daiyuanjing
 */
@EnableTransactionManagement(proxyTargetClass = true)
@AutoConfiguration
@Slf4j
@RequiredArgsConstructor
public class DyMybatisPlusAutoConfiguration {

    private final DyProperties dyProperties;

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor(TenantSupport tenantSupport){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        // 注意顺序问题

        if (dyProperties.getTenant().getEnabled().equals(Boolean.TRUE)){
            // 配置多租户
            tenantSupport.support(dyProperties,mybatisPlusInterceptor);
        }
        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置分页最大数量限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 乐观锁
        if (dyProperties.getCrud().getOptimisticLock().getEnabled().equals(Boolean.TRUE)){
            mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        }

        // 防止全表更新和删除
        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    /**
     * 自定义Id生成器 雪花Id
     */
    @Bean
    @ConditionalOnMissingBean
    public IdentifierGenerator identifierGenerator(){
        IdGeneratorStrategyEnum strategy = dyProperties.getCrud().getIdGenerator().getStrategy();
        if (strategy == IdGeneratorStrategyEnum.SNOWFLAKE){
            return new DySnowFlakeIdGenerateHandler(dyProperties);
        }
        if (strategy == IdGeneratorStrategyEnum.SEQUENCE){
            return new DySequenceIdGenerateHandler();
        }
        throw new IllegalArgumentException("非法id生成器策略参数");
    }

    /**
     * 字段自动填充
     */
    @Bean
    @ConditionalOnMissingBean
    public MyBatisPlusAutoFillHandler myBatisPlusAutoFillHandler(){
        return new MyBatisPlusAutoFillHandler();
    }

    /**
     * 默认租户支持
     */
    @Bean
    @ConditionalOnMissingBean
    public TenantSupport defaultTenantSupport(){
        return new DefaultTenantSupport();
    }
}
