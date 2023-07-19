package com.dy.framework.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.dy.framework.core.constant.DyConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类 ， 默认含[行级租户id]
 * T 主键类型
 * @author daiyuanjing
 * @date 2023-07-19 23:21
 */
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class DyBaseEntity<T extends Serializable> implements Serializable {

    private final static long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private T id;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户Id")
    @TableField(value = DyConstant.CRUD.COLUMN_TENANT_ID,fill = FieldFill.INSERT)
    private Long tenantId;

    /**
     * 乐观锁
     * 需自行加@Version注解生效
     */
    @ApiModelProperty(value = "乐观锁",notes = "需加@Version生效")
    @TableField(value = "revision")
    private Long revision;

    /**
     * 逻辑删除标识
     */
    @ApiModelProperty(value = "逻辑删除标识")
    @TableLogic
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = DyConstant.CRUD.COLUMN_CREATE_TIME,fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DyConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = DyConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField(value = DyConstant.CRUD.ENTITY_FILED_UPDATE_BY,fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = DyConstant.CRUD.COLUMN_UPDATE_TIME,fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = DyConstant.Jackson.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = DyConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    @TableField(value = DyConstant.CRUD.COLUMN_UPDATE_BY,fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;


}
