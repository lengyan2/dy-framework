package com.dy.framework.core.constant;

/**
 *  基础常量
 * @author daiyuanjing
 * @date 2023-07-18 23:31
 */
public interface DyConstant {



    interface Tenant {
        /**
         * 该租户Id为超级租户，可以无视租户sql拦截器
         */
        Long DEFAULT_PRIVILEGED_TENANT_ID = 0L;
    }
}
