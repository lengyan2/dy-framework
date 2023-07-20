package com.dy.framework.tenant.support;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.dy.framework.core.constant.DyConstant;
import com.dy.framework.core.context.TenantContextHolder;
import com.dy.framework.core.props.DyProperties;
import com.dy.framework.crud.support.TenantSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.Collection;
import java.util.Objects;

/**
 * 多租户支持 行级
 */
@Slf4j
public class TenantLineSupport implements TenantSupport {
    @Override
    public void support(DyProperties dyProperties, MybatisPlusInterceptor interceptor) {
        Collection<String> ignoreTables = dyProperties.getTenant().getIgnoreTables();

        // 添加行级租户内联拦截器
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(
                new DyLineTenantHandler(dyProperties.getTenant().getPrivilegedTenantId()
                , ignoreTables)
        ));
        log.info("\n\n[多租户支持] >> 隔离级别: 行级");
    }

    @AllArgsConstructor
    public static class DyLineTenantHandler implements TenantLineHandler{
        /**
         * 特权租户id
         */
        private final Long privilegedTenantId;

        /**
         * 忽略租户隔离的表
         */
        private final Collection<String> ignoreTables;

        @Override
        public Expression getTenantId() {
            Long currentTenantId = TenantContextHolder.getTenantId();
            if (currentTenantId == null){
                return  null;
            }
            return new LongValue(currentTenantId);
        }

        @Override
        public String getTenantIdColumn() {
            return DyConstant.CRUD.COLUMN_TENANT_ID;
        }

        @Override
        public boolean ignoreTable(String tableName) {
            Long tenantId = TenantContextHolder.getTenantId();
            if(Objects.nonNull(privilegedTenantId) && Objects.nonNull(tenantId) && privilegedTenantId.equals(tenantId)){
                return true;
            }
            return ignoreTables.contains(tableName);
        }
    }
}
