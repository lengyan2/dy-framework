package com.dy.framework.web.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**Jacksonl类型转化器自动配置类
 *
 * @author daiyuanjing
 * @date 2023-07-30 21:46
 */
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfiguration
public class MessageConverterAutoConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    /**
     * 使用jackson作为json messageConverter
     * 消失转换，内置断点续传 下载和字符串
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf( x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new ResourceRegionHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
