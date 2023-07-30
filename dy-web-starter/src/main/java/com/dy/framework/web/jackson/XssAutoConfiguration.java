package com.dy.framework.web.jackson;

import com.dy.framework.web.xss.XssFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;

/**
 * Xss 注入自动配置
 *
 * @author daiyuanjing
 * @date 2023-07-30 21:50
 */
@AutoConfiguration
public class XssAutoConfiguration {

     @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<?> xssFilterRegistration(){
         FilterRegistrationBean<XssFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
         filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
         filterFilterRegistrationBean.setFilter(new XssFilter());
         filterFilterRegistrationBean.addUrlPatterns("/*");
         filterFilterRegistrationBean.setName("xssFilter");
         filterFilterRegistrationBean.setOrder(Integer.MAX_VALUE);
         return filterFilterRegistrationBean;
     }
}
