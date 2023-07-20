package com.dy.framework.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.framework.crud.entity.DyBaseEntity;
import com.dy.framework.crud.service.DyBaseService;

/**
 * 服务基础模板实现类
 * 解决更新时 updateWrapper 自动填充字段不生效问题
 */
public class DyBaseServiceImpl<M extends BaseMapper<E> , E extends DyBaseEntity<?>> extends ServiceImpl<M,E> implements DyBaseService<E> {


    @Override
    public boolean update(Wrapper<E> updateWrapper) {
        E entity = updateWrapper.getEntity();
        if (null == entity){
            try {
                entity = this.currentModelClass().newInstance();
            }catch (InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return update(entity,updateWrapper);
    }
}
