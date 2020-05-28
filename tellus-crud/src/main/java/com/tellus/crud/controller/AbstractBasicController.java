package com.tellus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Strings;
import com.tellus.config.dozer.PageDozerGenerator;
import com.tellus.crud.service.ICustomizeService;
import com.tellus.crud.support.EntityUtil;
import com.tellus.crud.support.condition.FactorKit;
import com.tellus.support.annotation.IQueries;
import com.tellus.support.annotation.IUniqueness;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.exception.NotFoundException;
import com.tellus.support.exception.NotMatchException;
import com.tellus.toolkit.util.SpringRequestUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.tellus.support.enums.SystemErrorCodeEnum.UNIQUENESS;

/**
 * 基础 Controller 提供地城通用实现
 *
 * @author Roy
 * @date 2020/5/25 23:53
 */
public abstract class AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================

    @Resource
    protected PageDozerGenerator dozerGenerator;
    @Resource
    protected ICustomizeService<T> customizeService;

    // ~ Override/Protected Methods
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
     * @return Entity
     */
    protected <V> T convert(V vo, Class<T> entityClass) {
        return this.dozerGenerator.convert(vo, entityClass);
    }

    /**
     * 构建查询 wrapper
     *
     * @param info        VO
     * @param entityClass 实体
     * @param <V>         VO
     * @return QueryWrapper<T>
     */
    protected <V> QueryWrapper<T> builderWrapper(V info, Class<T> entityClass) {

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

    /**
     * 根据 TableId, IPrimaryKey, 查询资源是否存在
     *
     * @param entity 实体
     */
    protected void checkExistenceByPrimaryKey(T entity) {
        Integer id = EntityUtil.getIdToInteger(entity);
        checkExistenceByPrimaryKey(id);
    }

    /**
     * 根据主键查询资源是否存在
     *
     * @param id 主键
     */
    protected void checkExistenceByPrimaryKey(Serializable id) {
        this.customizeService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Resource does not exist," +
                        " %s, id: %s", getEntityClass(), id)));
    }

    /**
     * 根据VO参数, 校验数据唯一性
     *
     * @param s   VO
     * @param <S> S
     */
    protected <S> void checkUniqueness(S s) {
        IUniqueness uniquenessClass = s.getClass().getAnnotation(IUniqueness.class);
        if (null == uniquenessClass) {
            return;
        }

        OperationTypeEnum operationType = uniquenessClass.operationType();
        Map<IUniqueness, QueryWrapper<T>> wrappers = FactorKit.builderQueryWrapperForUniqueness(s);
        for (Map.Entry<IUniqueness, QueryWrapper<T>> entry : wrappers.entrySet()) {
            IUniqueness uniqueness = entry.getKey();
            List<T> list = this.customizeService.list(entry.getValue());

            if (null != list && !list.isEmpty()) {
                if (list.size() == 1 && operationType == OperationTypeEnum.UPDATED) {
                    Integer id = (Integer) EntityUtil.getTableId(list.get(0));
                    Integer incomingId = (Integer) EntityUtil.getPrimaryKey(s);

                    if (Objects.equals(id, incomingId)) {
                        return;
                    }
                }

                String message = Strings.isNullOrEmpty(uniqueness.message())
                        ? String.format(UNIQUENESS.getDescription(), uniqueness.value())
                        : uniqueness.message();

                throw new NotMatchException(UNIQUENESS.getCode(), message);
            }
        }
    }

    /**
     * ENTITY 实体
     *
     * @return 实体
     */
    protected abstract Class<T> getEntityClass();

    // ~ Private Methods
    // ==============================================================================

}
