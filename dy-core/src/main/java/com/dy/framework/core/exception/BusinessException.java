package com.dy.framework.core.exception;

import cn.hutool.core.util.StrUtil;
import com.dy.framework.core.enums.DyBaseEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

/**
 * 业务异常类
 *
 * @author daiyuanjing
 * @date 2023-07-18 22:27
 */
@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

 private Integer code = null;

 /**
  * 使用枚举类参数的构造方法创建的异常，记录对应枚举类及模板参数
  */
 private Enum<?> customEnumField;

 private Object[] templateParams;

 /**
  * 仅有错误信息
  * @param msg 错误信息
  */
 public BusinessException(String msg){
  super(msg);
  this.code = HTTP_INTERNAL_ERROR;
 }

 /**
  * 有错误信息和错误码
  * @param code 错误码
  * @param msg 错误信息
  */
 public BusinessException(int code,String msg){
  super(msg);
  this.code = code;
 }

 /**
  * 错误码 + 错误信息
  * @param code 错误码
  * @param msg 错误信息
  * @param templateParams 模板参数
  */
 public BusinessException(int code,String msg,Object... templateParams){
  super(StrUtil.format(msg,templateParams));
  this.code = code;
 }

 /**
  * 从枚举类中生成异常
  * @param customEnum 枚举类对象
  */
 public BusinessException(@NonNull DyBaseEnum<Integer> customEnum){
  super(customEnum.getLabel());
  this.code = customEnum.getValue();
}

public BusinessException(@NotNull DyBaseEnum<Integer> customEnum, Object... templateParams){
  super(StrUtil.format(customEnum.getLabel(),templateParams));
  this.code = customEnum.getValue();
}

 @Override
 public synchronized Throwable fillInStackTrace() {
  return this;
 }
}
