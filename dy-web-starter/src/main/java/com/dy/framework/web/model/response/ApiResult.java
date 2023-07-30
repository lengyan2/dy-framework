package com.dy.framework.web.model.response;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.dy.framework.core.constant.DyConstant;
import com.dy.framework.core.enums.DyBaseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * HTTP接口通用返回对象
 *
 * @author daiyuanjing
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResult<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "承载数据")
    private T data;

    public static <T> ApiResult<T> success() {
        return build(HttpStatus.HTTP_OK, DyConstant.Message.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(String msg) {
        return build(HttpStatus.HTTP_OK, msg, null);
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        return build(code, msg, null);
    }

    public static <T> ApiResult<T> fail(Integer code, String msg, T data) {
        return build(code, msg, data);
    }

    public static <T> ApiResult<T> data(T data) {
        return build(HttpStatus.HTTP_OK, ObjectUtil.isEmpty(data) ? DyConstant.Message.NULL : DyConstant.Message.SUCCESS, data);
    }

    public static <T> ApiResult<T> data(String msg, T data) {
        return build(HttpStatus.HTTP_OK, msg, data);
    }

    public static <T> ApiResult<T> build(DyBaseEnum<Integer> enumItem) {
        return build(enumItem.getValue(), enumItem.getLabel(), null);
    }

    public static <T> ApiResult<T> build(DyBaseEnum<Integer> enumItem, T data) {
        return build(enumItem.getValue(), enumItem.getLabel(), data);
    }

    private static <T> ApiResult<T> build(Integer code, String msg, T data) {
        ApiResult<T> ret = new ApiResult<>();
        ret
                .setCode(code)
                .setMsg(msg)
                .setData(data);

        return ret;
    }
}
