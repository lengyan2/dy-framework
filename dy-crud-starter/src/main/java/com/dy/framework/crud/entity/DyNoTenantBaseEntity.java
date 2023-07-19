package com.dy.framework.crud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dy.framework.core.constant.DyConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**去除租户id
 *
 * @author daiyuanjing
 * @date 2023-07-19 23:34
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class DyNoTenantBaseEntity<T extends Serializable> extends DyBaseEntity<T> {

    /**
     * 覆盖不显示租户id
     */
    @TableField(value = DyConstant.CRUD.COLUMN_TENANT_ID,exist = false)
    private Long tenantId;
}
