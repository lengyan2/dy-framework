package com.dy.framework.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**mvc 自动配置
 *
 * @author daiyuanjing
 * @date 2023-07-30 21:13
 */
@Slf4j
@AutoConfiguration
public class DefaultWebMvcAutoConfiguration implements WebMvcConfigurer, WebBindingInitializer {
    /**
     * get请求 将所有参数空格trim
     * @param binder
     */
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(false));
    }

    /**
     * 自定义静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry
                .addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/swagger-resources")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
