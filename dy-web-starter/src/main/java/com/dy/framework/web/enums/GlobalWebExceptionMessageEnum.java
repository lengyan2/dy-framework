package com.dy.framework.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Web 全局异常处理消息枚举
 *
 * @author daiyuanjing
 * @date 2023-07-30 17:16
 */
@Getter
@AllArgsConstructor
public enum GlobalWebExceptionMessageEnum {

    GLOBAL_NO_LOGIN("请先登录"),
    GLOBAL_PERMISSION_NOT_MATCH("权限不足"),
    GLOBAL_ROLE_NOT_MATCH("您与要求角色不匹配"),
    GLOBAL_NOT_FOUND("404界面不存在"),
    GLOBAL_UNACCEPTABLE_PARAMTERS("错误参数格式"),
    GLOBAL_METHOD_NOT_ALLOWED("请求方式错误"),

    GLOBAL_INTERNAL_ERROR("请稍后再试");

    private final String value;



}
