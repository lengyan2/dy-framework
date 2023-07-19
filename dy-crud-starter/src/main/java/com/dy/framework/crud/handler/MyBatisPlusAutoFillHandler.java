package com.dy.framework.crud.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dy.framework.core.constant.DyConstant;
import com.dy.framework.core.context.TenantContextHolder;
import com.dy.framework.core.context.UserContextHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * mybatis自动填充字段
 * @author daiyuanjing
 * @date 2023-07-20 0:07
 */
public class MyBatisPlusAutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,DyConstant.CRUD.COLUMN_TENANT_ID,Long.class,TenantContextHolder.getTenantId());
        this.strictInsertFill(metaObject,DyConstant.CRUD.COLUMN_CREATE_TIME, LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,DyConstant.CRUD.COLUMN_UPDATE_TIME, LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,DyConstant.CRUD.COLUMN_CREATE_BY,Long.class, UserContextHolder.getUserId());
        this.strictInsertFill(metaObject,DyConstant.CRUD.COLUMN_UPDATE_BY,Long.class,UserContextHolder.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,DyConstant.CRUD.COLUMN_UPDATE_BY,Long.class,UserContextHolder.getUserId());
        this.strictUpdateFill(metaObject,DyConstant.CRUD.COLUMN_UPDATE_TIME,LocalDateTime.class,LocalDateTime.now());
    }
}
