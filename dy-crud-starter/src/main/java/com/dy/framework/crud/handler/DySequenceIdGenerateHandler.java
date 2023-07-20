package com.dy.framework.crud.handler;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.dy.framework.core.enums.IdGeneratorStrategyEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义Id生成器 mybatis 自带
 * @author daiyuanjing
 * @date 2023-07-19 23:51
 */
@Slf4j
public class DySequenceIdGenerateHandler extends DefaultIdentifierGenerator {

 public DySequenceIdGenerateHandler(){
     super();
     log.info("【主键id生成器】 >> strategy=[{}]", IdGeneratorStrategyEnum.SEQUENCE);
 }
}
