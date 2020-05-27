package com.tellus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Strings;
import com.tellus.crud.service.ICustomizeService;
import com.tellus.crud.support.EntityUtils;
import com.tellus.crud.support.PageUtils;
import com.tellus.crud.support.condition.FactorKit;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.annotation.IUniqueness;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.exception.NotFoundException;
import com.tellus.support.exception.NotMatchException;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.tellus.support.enums.SystemErrorCodeEnum.UNIQUENESS;

/**
 * 通用 CRUD Controller
 * <p>
 * V - Result VO 结果
 * R - Retrieve VO 读取
 * U - Update VO 更新
 * C - Create VO 创建
 * <p>
 * 删除数据: 仅支持根据 ID 删除
 *
 * @author Roy
 * @date 2020/5/27 20:29
 */
@Validated
public class AbstractCrudController<T, V, C, R, U> extends BasicController {

    // ~ Static fields/initializers
    // ==============================================================================

    @Autowired
    protected ICustomizeService<T> customizeService;

    // ~ Common Methods
    // ==============================================================================

    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录", notes = "分页查询记录通用 Restful API")
    public PageWrapper<V> page(@Valid @RequestBody PageInfo<R> pageIno) {
        //  构建 wrapper 对象
        QueryWrapper<T> wrapper = this.builderWrapper(pageIno.getQueries(), getEntityClass());

        IPage<T> result = this.customizeService
                .page(PageUtils.builderOrderUpperUnderscore(pageIno), wrapper);

        return this.dozerGenerator.convert(result, getVoClass());
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询列表记录", notes = "查询列表记录通用 Restful API")
    public List<V> list(@Valid R retrieveVO) {
        QueryWrapper<T> wrapper = this.builderWrapper(retrieveVO, getEntityClass());
        return this.dozerGenerator.convert(this.customizeService.list(wrapper), getVoClass());
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计总数", notes = "根据条件统计总记录数 Restful API")
    public Integer count(@Valid R retrieveVO) {
        QueryWrapper<T> wrapper = this.builderWrapper(retrieveVO, getEntityClass());
        return this.customizeService.count(wrapper);
    }

    @IOperationLog(theme = "根据 ID 删除一条记录", type = OperationTypeEnum.DELETED, operator = "#{#username}")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除记录", notes = "根据 ID 删除一条记录 Restful API")
    public Boolean removeById(@SuppressWarnings("unused") @RequestHeader("username") String username,
                              @PathVariable(value = "id") Integer id) {
        return this.customizeService.removeById(id);
    }

    @IOperationLog(theme = "根据 ID 批量删除记录", type = OperationTypeEnum.DELETED, operator = "#{#username}")
    @PostMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量删除记录", notes = "根据 ID 批量删除记录 Restful API")
    @ApiImplicitParam(name = "ids", value = "ID集合", required = true, example = "[1,2,3,4]", dataType = "Integer[]")
    public Boolean removeByIds(@SuppressWarnings("unused") @RequestHeader("username") String username,
                               @Valid @NotEmpty(message = "ID集合") Set<Integer> ids) {
        return this.customizeService.removeByIds(ids);
    }

    @IOperationLog(theme = "创建一条记录", type = OperationTypeEnum.CREATED, operator = "#{#username}")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "创建一条记录", notes = "创建一条记录 Restful API")
    public Integer save(@RequestHeader("username") String username,
                        @Valid @RequestBody C cratedVO) {

        return null;
    }

    // ~ Override Methods
    // ==============================================================================

    /**
     * 根据 TableId, IPrimaryKey, 查询资源是否存在
     *
     * @param entity 实体
     */
    protected void checkExistenceByPrimaryKey(T entity) {
        Integer id = EntityUtils.getIdToInteger(entity);
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
                    Integer id = (Integer) EntityUtils.getTableId(list.get(0));
                    Integer incomingId = (Integer) EntityUtils.getPrimaryKey(s);

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
    protected Class<T> getEntityClass() {
        //noinspection unchecked
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * 返回结果 VO
     *
     * @return V 返回结果VO
     */
    protected Class<V> getVoClass() {
        //noinspection unchecked
        return (Class<V>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }

    /**
     * 创建 VO
     *
     * @return 创建 VO
     */
    protected Class<C> getCoClass() {
        //noinspection unchecked
        return (Class<C>) ReflectionKit.getSuperClassGenericType(getClass(), 3);
    }

    /**
     * 查询 VO
     *
     * @return 查询 VO
     */
    protected Class<R> getRoClass() {
        //noinspection unchecked
        return (Class<R>) ReflectionKit.getSuperClassGenericType(getClass(), 4);
    }

    /**
     * 更新 VO
     *
     * @return 更新 VO
     */
    protected Class<U> getUoClass() {
        //noinspection unchecked
        return (Class<U>) ReflectionKit.getSuperClassGenericType(getClass(), 5);
    }
}














