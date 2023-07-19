package com.dy.framework.core.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 当前租户上下文对象
 *
 * @author daiyuanjing
 * @date 2023-07-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuperBuilder
public class TenantContext implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CAMEL_NAME = "tenantContext";
    @ApiModelProperty(value = "租户Id")
    private Long tenantId;

    @ApiModelProperty(value = "租户名")
    private String tenantName;
}
