package com.tellus.permission.cloud.controller;

import cn.hutool.core.util.ReflectUtil;
import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 层级关系通用 Controller
 *
 * @author Roy
 * @date 2020/7/16 10:31
 */
public abstract class AbstractRelationController<V, S, R, U> extends BaseRelationController {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IBasicRelationService<V, S, R, U> basicRelationService;

    // ~ Constructors
    // ==============================================================================

    public AbstractRelationController(IBasicRelationService<V, S, R, U> basicRelationService) {
        this.basicRelationService = basicRelationService;
    }

    // ~ Methods
    // ==============================================================================

    @PostMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录")
    public Result<PageWrapper<V>> pages(@Valid @RequestBody PageInfo<R> pageInfo) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        //  数据权限校验
        checkPermission(pageInfo.getQueries());

        return Result.success(basicRelationService.page(pageInfo));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询记录列表")
    public Result<List<V>> list(@Valid R retrieveVO) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        retrieveVO = newInstance(retrieveVO);

        //  数据权限校验
        checkPermission(retrieveVO);

        return Result.success(basicRelationService.list(retrieveVO));
    }

    @IOperationLog(type = OperationTypeEnum.CREATED, theme = "保存一条记录")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存一条记录", notes = "保存一条记录")
    public Result<Integer> save(@Valid @RequestBody S saveVO) {
        //  数据权限校验
        checkPermission(saveVO);
        Integer result = basicRelationService.save(saveVO);
        return Result.success(result);
    }

    @IOperationLog(type = OperationTypeEnum.UPDATED, theme = "更新一条记录")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新一条记录", notes = "根据ID更新一条记录")
    public Result<Boolean> update(@Valid @RequestBody U updateVO) {
        //  数据权限校验
        checkPermission(updateVO);
        boolean result = basicRelationService.updateById(updateVO);
        return Result.success(result);
    }

    @IOperationLog(type = OperationTypeEnum.DELETED, theme = "删除一条记录")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除记录", notes = "根据ID删除一条记录")
    public Result<Boolean> removeById(@PathVariable("id") Integer id) {
        //  数据权限校验
        checkWhetherSubordinate(id);

        boolean result = basicRelationService.removeById(id);
        return Result.success(result);
    }

    // ~ Protected/Override Methods
    // ==============================================================================

    @SuppressWarnings("unchecked")
    protected Class<V> getVOClass() {
        return (Class<V>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    @SuppressWarnings("unchecked")
    protected Class<S> getSOClass() {
        return (Class<S>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }

    @SuppressWarnings("unchecked")
    protected Class<R> getROClass() {
        return (Class<R>) ReflectionKit.getSuperClassGenericType(getClass(), 3);
    }

    @SuppressWarnings("unchecked")
    protected Class<U> getUOClass() {
        return (Class<U>) ReflectionKit.getSuperClassGenericType(getClass(), 4);
    }

    protected R newInstance(R vo) {
        return null == vo ? ReflectUtil.newInstance(getROClass()) : vo;
    }
}
