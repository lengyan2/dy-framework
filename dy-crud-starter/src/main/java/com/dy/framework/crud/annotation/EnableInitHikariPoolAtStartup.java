package com.dy.framework.crud.annotation;

import com.dy.framework.crud.config.InitHikariPoolAtStartupConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 在项目启动时直接初始化hikari连接池 关闭按需连接
 * @author daiyuanjing
 * @date 2023-07-19 20:15
 */
@Import({InitHikariPoolAtStartupConfiguration.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface EnableInitHikariPoolAtStartup {
}
