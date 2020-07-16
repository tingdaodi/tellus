package com.tellus.permission.cloud.controller;

import com.tellus.permission.cloud.support.IBasicService;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller 通用实现
 * <p>
 * V - Result VO
 * S - Save VO
 * R - Retrieve VO
 * U - Update VO
 *
 * @author Roy
 * @date 2020/7/16 8:42
 */
@Validated
public abstract class AbstractCrudController<V, S, R, U> extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    public final IBasicService<V, S, R, U> basicService;

    // ~ Constructors
    // ==============================================================================

    public AbstractCrudController(IBasicService<V, S, R, U> basicService) {
        this.basicService = basicService;
    }

    // ~ Methods
    // ==============================================================================

    @PostMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录")
    public Result<PageWrapper<V>> pages(@Valid @RequestBody PageInfo<R> pageInfo) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        return Result.success(basicService.page(pageInfo));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询记录列表")
    public Result<List<V>> list(@Valid R retrieveVO) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        return Result.success(basicService.list(retrieveVO));
    }

    @IOperationLog(type = OperationTypeEnum.CREATED, theme = "保存一条记录")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存一条记录", notes = "保存一条记录")
    public Result<Integer> save(@Valid @RequestBody S saveVO) {
        Integer result = basicService.save(saveVO);
        return Result.success(result);
    }

    @IOperationLog(type = OperationTypeEnum.UPDATED, theme = "更新一条记录")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新一条记录", notes = "根据ID更新一条记录")
    public Result<Boolean> update(@Valid @RequestBody U updateVO) {
        boolean result = basicService.updateById(updateVO);
        return Result.success(result);
    }

    @IOperationLog(type = OperationTypeEnum.DELETED, theme = "删除一条记录")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除记录", notes = "根据ID删除一条记录")
    public Result<Boolean> removeById(@PathVariable("id") Integer id) {
        boolean result = basicService.removeById(id);
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
}
