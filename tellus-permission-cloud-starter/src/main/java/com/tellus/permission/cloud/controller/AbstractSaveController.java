package com.tellus.permission.cloud.controller;

import com.tellus.permission.cloud.support.ISaveService;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 保存记录 Controller 通用实现
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
public abstract class AbstractSaveController<S> extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    public final ISaveService<S> saveService;

    // ~ Constructors
    // ==============================================================================

    public AbstractSaveController(ISaveService<S> saveService) {
        this.saveService = saveService;
    }

    // ~ Methods
    // ==============================================================================

    @IOperationLog(type = OperationTypeEnum.CREATED, theme = "保存一条记录")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存一条记录", notes = "保存一条记录")
    public Result<Integer> save(@Valid @RequestBody S saveVO) {
        Integer result = saveService.save(saveVO);
        return Result.success(result);
    }

    // ~ Protected/Override Methods
    // ==============================================================================

    @SuppressWarnings({"unchecked"})
    protected Class<S> getSOClass() {
        return (Class<S>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

}
