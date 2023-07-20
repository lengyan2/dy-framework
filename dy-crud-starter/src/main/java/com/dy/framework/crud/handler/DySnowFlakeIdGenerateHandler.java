package com.dy.framework.crud.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.dy.framework.core.enums.IdGeneratorStrategyEnum;
import com.dy.framework.core.props.DyProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 自定义Id生成器 雪花算法
 * @author daiyuanjing
 * @date 2023-07-19 23:54
 */
@Slf4j
public class DySnowFlakeIdGenerateHandler implements IdentifierGenerator {

    private final Snowflake snowflake;


    public DySnowFlakeIdGenerateHandler(DyProperties dyProperties){
        long workerId;

        try {
            // 获取本地ip
            workerId =  NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            workerId = NetUtil.getLocalhost().hashCode();
        }
        workerId = workerId % 32;
        Long dataCenterId = dyProperties.getCrud().getIdGenerator().getDatacenterId();
        String epochDateStr = dyProperties.getCrud().getIdGenerator().getEpochDate();
        Date epochDate = DateUtil.parseDate(epochDateStr);

        log.info("【主键ID生成器】>> strategy=[{}],workId=[{}],datacenterId=[{}],epochDate=[{}]", IdGeneratorStrategyEnum.SNOWFLAKE,workerId,dataCenterId,epochDate);

        snowflake = getSnowFlake(workerId,dataCenterId,epochDate);

    }

    @Override
    public Number nextId(Object entity) {
        return snowflake.nextId();
    }

    @Override
    public String nextUUID(Object entity) {
        return IdUtil.randomUUID();
    }

    private static Snowflake getSnowFlake(long workId,Long dataCenterId,Date epochDate){
        return Singleton.get(Snowflake.class,epochDate,workId,dataCenterId,false);
    }
}
