package com.dy.framework.web.listener;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**项目启动事件通知
 *
 * @author daiyuanjing
 * @date 2023-07-30 18:05
 */
@Slf4j
public class LaunchEventListener {

    @Async
    @Order
    @EventListener(WebServerInitializedEvent.class)
    public void afterStart(WebServerInitializedEvent event){
        Environment environment = event.getApplicationContext().getEnvironment();
        int port = event.getWebServer().getPort();

        String protocol = "http";
        if (environment.getProperty("server.ssl.key-stroe") != null){
            protocol = "https";
        }
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (StrUtil.isBlank(contextPath)){
            contextPath = "/";
        }
        String externalHost = "localhost";
        try {
            externalHost = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            log.warn("获取IP失败");
        }
        System.out.println(
                StrUtil.format(
                        "\n"+
                                "----------------------------------------------------------" +
                                "\n\tApplication '{}' is running! Access URLs:" +
                                "\n\tLocal: \t\t{}://127.0.0.1:{}{}" +
                                "\n\tExternal: \t{}://{}:{}{}" +
                                "\n----------------------------------------------------------" +
                                "\n",
                        environment.getProperty("spring.application.name"),
                        protocol,
                        port,
                        contextPath,
                        protocol,
                        externalHost,
                        port,
                        contextPath
                ));
    }

}
