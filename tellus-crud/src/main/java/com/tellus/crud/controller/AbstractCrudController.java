package com.tellus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tellus.crud.support.PageUtil;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

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
public abstract class AbstractCrudController<T, V, C, R, U> extends AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================


    // ~ Common Methods
    // ==============================================================================

    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录", notes = "分页查询记录通用 Restful API")
    public PageWrapper<V> page(@Valid @RequestBody PageInfo<R> pageIno) {
        //  构建 wrapper 对象
        QueryWrapper<T> wrapper = this.builderWrapper(pageIno.getQueries(), getEntityClass());

        IPage<T> result = this.customizeService
                .page(PageUtil.builderOrderUpperUnderscore(pageIno), wrapper);

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
    public Integer save(@SuppressWarnings("unused") @RequestHeader("username") String username,
                        @Valid @RequestBody C cratedVO) {
        //  校验数据唯一性
        checkUniqueness(cratedVO);
        T entity = this.convert(cratedVO, getEntityClass());
        return this.customizeService.saveWithReturnId(entity);
    }

    @IOperationLog(theme = "更新记录", type = OperationTypeEnum.UPDATED, operator = "#{#username}")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新记录", notes = "更新一条记录 Restful API")
    public Boolean update(@RequestHeader("username") String username,
                          @Valid @RequestBody U updatedVO) {
        //  校验数据唯一性
        checkUniqueness(updatedVO);
        T entity = this.convert(updatedVO, getEntityClass());
        return this.customizeService.updateById(entity);
    }

    // ~ Override/Protected Methods
    // ==============================================================================

    /**
     * ENTITY 实体
     *
     * @return 实体
     */
    @Override
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

    // ~ Private Methods
    // ==============================================================================
}














