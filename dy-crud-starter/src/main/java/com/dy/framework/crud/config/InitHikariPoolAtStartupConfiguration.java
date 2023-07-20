package com.dy.framework.crud.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 在项目启动时之际初始化 hikari连接池 否则默认为懒加载
 * @author daiyuanjing
 * @date 2023-07-19 20:16
 */
public class InitHikariPoolAtStartupConfiguration {

    @Bean
    public ApplicationRunner runner(DataSource dataSource){
        return args -> dataSource.getConnection();
    }
}
