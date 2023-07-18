package com.dy.framework.core.props;

import com.dy.framework.core.constant.DyConstant;
import com.dy.framework.core.enums.IdGeneratorStrategyEnum;
import com.dy.framework.core.enums.TenantIsolateLevelEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author daiyuanjing
 * @date 2023-07-18 21:43
 */
@ConfigurationProperties(prefix = "dy")
@Data
public class DyProperties {



    @Data
    public static class Security {

        /**
         * 放行路由地址，不进行登录校验
         */
        private final List<String> excludeRoutes = new ArrayList<>(64);

    }

    @Data
    public static class Crud{
        /**
         * 乐观锁插件
         */
        private final OptimisticLock optimisticLock = new OptimisticLock();

        /**
         * Id生成器
         */
        private final IdGenerator idGenerator = new IdGenerator();

        /**
         * 数据源 默认mysql
         */
        private String dbType = "mysql";

    }

    @Data
    public static class Knife4j {
        /**
         * knife4j UI 上显示的标题
         */
        private String title = "";

        /**
         * Knife4j 显示描述
         */
        private String description = "";

        /**
         * Knife4j version
         */
        private String version = "";

    }



    @Data
    public static class OptimisticLock{

        /**
         * 是否启用乐观锁 默认为true
         */
        private Boolean enabled = true;
    }

    @Data
    public static class IdGenerator{
        /**
         * Id生成器策略 默认为SNOWFLAKE
         */
        private IdGeneratorStrategyEnum strategy = IdGeneratorStrategyEnum.SNOWFLAKE;

        /**
         * 雪花算法起始时刻
         */
        private String epochDate = "2023-01-01";

        /**
         * 雪花算法- 数据中心ID 默认是0
         */
        private Long datacenterId = 0L;

    }

    @Data
    public static class Tenant{
        /**
         * 是否开启多租户
         */
        private Boolean enabled = false;

        /**
         * 多租户隔离级别 默认为行级
         */
        private TenantIsolateLevelEnum isolateLevel = TenantIsolateLevelEnum.LINE;

        /**
         * 那些表忽略租户隔离 [行级]生效
         */
        private Collection<String> ignoreTables = new LinkedHashSet<>(64);

        /**
         * 特权租户id 租户可以无视租户sql拦截器 [行级]生效
         */
        private Long privilegedTenantId = DyConstant.Tenant.DEFAULT_PRIVILEGED_TENANT_ID;
    }

    @Data
    public static class Web{
        private final Logging logging = new Logging();

        @Data
        public static class Logging{
            /**
             * 是否启动Web访问日志切面，默认为false
             */
            private Boolean enabled = Boolean.FALSE;
        }
    }

}
