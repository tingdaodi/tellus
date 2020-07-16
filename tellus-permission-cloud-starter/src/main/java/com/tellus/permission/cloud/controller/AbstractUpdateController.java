package com.tellus.permission.cloud.controller;

import com.tellus.permission.cloud.support.IUpdateService;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 更新记录 Controller 通用实现
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
public abstract class AbstractUpdateController<U> extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    public final IUpdateService<U> updateService;

    // ~ Constructors
    // ==============================================================================

    public AbstractUpdateController(IUpdateService<U> updateService) {
        this.updateService = updateService;
    }

    // ~ Methods
    // ==============================================================================

    @IOperationLog(type = OperationTypeEnum.UPDATED, theme = "更新一条记录")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新一条记录", notes = "根据ID更新一条记录")
    public Result<Boolean> update(@Valid @RequestBody U updateVO) {
        boolean result = updateService.updateById(updateVO);
        return Result.success(result);
    }

    @IOperationLog(type = OperationTypeEnum.DELETED, theme = "删除一条记录")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除记录", notes = "根据ID删除一条记录")
    public Result<Boolean> removeById(@PathVariable("id") Integer id) {
        boolean result = updateService.removeById(id);
        return Result.success(result);
    }

    // ~ Protected/Override Methods
    // ==============================================================================

    @SuppressWarnings("unchecked")
    protected Class<U> getUOClass() {
        return (Class<U>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }
}
