package com.dy.framework.minio.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@SpringBootConfiguration
@Import(MinioAutoConfiguration.class)
public @interface EnableMinioClient {
}