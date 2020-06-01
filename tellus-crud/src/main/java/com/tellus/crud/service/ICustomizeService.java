package com.tellus.crud.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.crud.support.EntityUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 扩充 IService
 *
 * @author Roy
 * @date 2020/5/25 22:42
 */
public interface ICustomizeService<T> extends IService<T> {

    /**
     * 是否存在
     *
     * @param id ID
     * @return Boolean
     */
    default Boolean contains(Integer id) {
        return null != this.getById(id);
    }

    /**
     * 根据主键查询资源
     *
     * @param id ID
     * @return Optional<T>
     */
    default Optional<T> findById(Serializable id) {
        return Optional.ofNullable(this.getById(id));
    }

    /**
     * 查询, 返回一条数据, 多条则抛出异常
     *
     * @param entity 条件
     * @return Optional<T>
     */
    default Optional<T> findOne(T entity) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.setEntity(entity);
        return Optional.ofNullable(this.getOne(wrapper, true));
    }

    /**
     * 插入一条数据, 返回ID
     *
     * @param entity 实体
     * @return Integer ID
     */
    default Integer saveWithReturnId(T entity) {
        return saveWithReturnId(entity, EntityUtil::getIdToInteger);
    }

    /**
     * 插入一条数据, 返回ID
     *
     * @param entity      实体
     * @param getIdMapper 获取 ID 表达式
     * @return Integer ID
     */
    default Integer saveWithReturnId(T entity, Function<T, Integer> getIdMapper) {
        this.save(entity);
        return getIdMapper.apply(entity);
    }

    /**
     * 查询列表
     *
     * @param entity 实体
     * @return List<T>
     */
    default List<T> list(T entity) {
        return list(new QueryWrapper<>(entity));
    }

    /**
     * 根据条件删除记录
     *
     * @param entity 实体
     * @return boolean
     */
    default boolean remove(T entity) {
        return this.remove(new QueryWrapper<>(entity));
    }

}
