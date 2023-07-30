package com.dy.framework.web.jackson;

import com.dy.framework.core.constant.DyConstant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列化规则
 *
 * @author daiyuanjing
 * @date 2023-07-30 17:53
 */
public class DyJacksonModule extends SimpleModule {

    public DyJacksonModule(){
        super();

        /**
         * 大整数转字符串
         */
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        this.addSerializer(BigDecimal.class, ToStringSerializer.instance);

        /**
         * 时间相关
         */
        this.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DyConstant.Jackson.DATE_TIME_FORMAT)));
        this.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(DyConstant.Jackson.DATE_FORMAT)));
        this.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DyConstant.Jackson.TIME_FORMAT)));
        this.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DyConstant.Jackson.DATE_FORMAT)));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DyConstant.Jackson.TIME_FORMAT)));
    }
}
