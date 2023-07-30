package com.dy.framework.core.constant;

/**
 *  基础常量
 * @author daiyuanjing
 * @date 2023-07-18 23:31
 */
public interface DyConstant {


    interface  Dubbo{

        String ENABLE_VALIDATION = "true";

        int TIMEOUT = 10000;

        int RETRIES = -1;

        int RPC_EXCEPTION_RESPONSE_CODE = 1;
    }

    interface Message{
        String NULL = "暂无数据";

        String SUCCESS = "操作成功";
    }

    interface Version{
        /**
         * HTTP API 版本v1
         */
        String HTTP_API_VERSION_V1 = "/api/v1";

        /**
         * DUBBO API 版本 v1
         */
        String DUBBO_VERSION_V1 = "1.0.0";
    }

    interface Jackson{
        String DATE_FORMAT = "yyyy-MM-dd";

        String TIME_FORMAT = "HH:mm:ss";

        String DATE_TIME_FORMAT = Jackson.DATE_FORMAT + " " + Jackson.TIME_FORMAT;
    }

    interface  CRUD{
        /**
         * 租户id
         */
        String COLUMN_TENANT_ID = "tenant_id";

        String ENTITY_FIELD_TENANT_ID = "tenantId";

        /**
         * 创建时间
         */
        String COLUMN_CREATE_TIME = "create_time";

        String ENTITY_FIELD_CREATE_TIME = "createTime";

        /**
         * 创建者
         */
        String COLUMN_CREATE_BY = "create_by";

        String ENTITY_FIELD_CREATE_BY = "createBy";

        /**
         * 更新时间
         */
        String COLUMN_UPDATE_TIME = "update_time";

        String ENTITY_FIELD_UPDATE_TIME = "updateTime";

        /**
         * 更新者
         */
        String COLUMN_UPDATE_BY = "update_by";

        String ENTITY_FILED_UPDATE_BY = "updateBy";

        /**
         * SQL Limit 1
         */
        String SQL_LIMIT_1 = " LIMIT 1";

    }

    interface Regex{
        String CHINA_MAINLAND_PHONE_NO = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    }

    interface Permission{
        String CREATE = "create";

        String RETRIEVE = "retrieve";

        String UPDATE = "update";

        String DELETE = "delete";

    }

    interface Tenant {
        /**
         * 该租户Id为超级租户，可以无视租户sql拦截器
         */
        Long DEFAULT_PRIVILEGED_TENANT_ID = 0L;
    }

    interface Minio{
        /**
         * minio 配置前缀
         */
        String PREFIX = "minio";
    }
}
