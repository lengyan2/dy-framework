package com.dy.framework.web.config;


import com.dy.framework.core.constant.DyConstant;
import com.dy.framework.web.jackson.DyJacksonModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * jackson自动配置类
 *
 * @author daiyuanjing
 * @date 2023-07-30 21:35
 */
@ConditionalOnClass(ObjectMapper.class)
@AutoConfiguration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class JacksonExtendAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){
        return new Jackson2ObjectMapperBuilder();
    }

    /**
     * 全局配置 序列化和反序列化规则
     */
    @Primary
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){
        builder.simpleDateFormat(DyConstant.Jackson.DATE_TIME_FORMAT);
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        Locale aDefault = Locale.getDefault();

        objectMapper.
                // 地点
                setLocale(aDefault)
                // 时区
                .setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                // 去掉默认的时间戳格式
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
                // Date参数日期格式
                .setDateFormat(new SimpleDateFormat(DyConstant.Jackson.DATE_TIME_FORMAT,aDefault))
                // 该特性决定parser是否允许JSON字符串是否包含非引号控制字符(值小于32的ASCII字符，包含制表符和换行符)
                // 如果该属性关闭，遇到这些字符就会抛出异常
                .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),true)
                // 忽略不能转换的字符
                .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true)
                // 在使用spring boot + jpa/hibernate，如果实体字段上加有FetchType.LAZY，并使用jackson序列化为json串时，会遇到SerializationFeature.FAIL_ON_EMPTY_BEANS异常
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // 忽略未知字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 单引号处理
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                // 字段排序
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                // 失败处理
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 引用前人写好的序列化/反序列化规则
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                // 自定义序列化/反序列化规则
                .registerModule(new DyJacksonModule())
                // 输出所有字段
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .findAndRegisterModules();
        return objectMapper;
    }
}
