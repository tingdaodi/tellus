package com.tellus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tellus.config.dozer.PageDozerGenerator;
import com.tellus.crud.support.condition.FactorKit;
import com.tellus.support.annotation.IQueries;
import com.tellus.toolkit.util.SpringRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础 Controller 提供地城通用实现
 *
 * @author Roy
 * @date 2020/5/25 23:53
 */
public class BasicController {

    // ~ Static fields/initializers
    // ==============================================================================

    @Autowired
    protected PageDozerGenerator dozerGenerator;

    // ~ Protected Methods
    // ==============================================================================

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    protected String getUsername() {
        return SpringRequestUtils.getHeaderValue("username");
    }

    /**
     * 将 VO 转换成 Entity
     *
     * @param vo          VO
     * @param entityClass 实体类型
     * @param <V>         VO
     * @param <T>         ENTITY
     * @return Entity
     */
    protected <V, T> T convert(V vo, Class<T> entityClass) {
        return this.dozerGenerator.convert(vo, entityClass);
    }

    /**
     * 构建查询 wrapper
     *
     * @param info        VO
     * @param entityClass 实体
     * @param <V>         VO
     * @param <T>         ENTITY
     * @return QueryWrapper<T>
     */
    protected <V, T> QueryWrapper<T> builderWrapper(V info, Class<T> entityClass) {

        if (null == info) {
            return Wrappers.emptyWrapper();
        }

        QueryWrapper<T> wrapper;
        IQueries iQueries = info.getClass().getAnnotation(IQueries.class);

        if (null == iQueries) {
            T entity = this.dozerGenerator.convert(info, entityClass);
            wrapper = Wrappers.query(entity);
        } else {
            wrapper = FactorKit.builderQueryWrapper(info, entityClass);
        }

        return wrapper;
    }

}
