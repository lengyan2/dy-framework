package com.dy.framework.minio.config;

import com.dy.framework.core.constant.DyConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio 配置类
 * @author daiyuanjing
 * @date 2023-07-30 22:31
 */
@Data
@ConfigurationProperties(prefix = DyConstant.Minio.PREFIX)
public class MinioProperties {

    /**
     * 断点,minio 地址
     */
    private String endpoint;

    /**
     * accessKey
     */
    private String  accessKey;

    /**
     * sercetKey
     */
    private String secretKey;

    /**
     * bucket
     */
    private String bucket;
}
