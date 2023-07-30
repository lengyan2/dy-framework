package com.dy.framework.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * minio自动配置
 *
 * @author daiyuanjing
 * @date 2023-07-30 22:23
 */

@AllArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
@Configuration
@ComponentScan("com.dy.framework.minio")
public class MinioAutoConfiguration {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(minioProperties.getEndpoint(),minioProperties.getAccessKey(),minioProperties.getSecretKey());
    }
}
