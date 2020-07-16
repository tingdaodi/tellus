package com.tellus.permission.cloud.controller;

import com.tellus.permission.cloud.support.IRetrieveService;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.Result;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 查询记录 Controller 通用实现
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
public abstract class AbstractRetrieveController<V, R> extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    public final IRetrieveService<V, R> retrieveService;

    // ~ Constructors
    // ==============================================================================

    public AbstractRetrieveController(IRetrieveService<V, R> retrieveService) {
        this.retrieveService = retrieveService;
    }

    // ~ Methods
    // ==============================================================================

    @PostMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录")
    public Result<PageWrapper<V>> pages(@Valid @RequestBody PageInfo<R> pageInfo) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        return Result.success(retrieveService.page(pageInfo));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询记录列表")
    public Result<List<V>> list(@Valid R retrieveVO) {

        //  TODO 自动注入时间, 平台等参数, 待实现

        return Result.success(retrieveService.list(retrieveVO));
    }


    // ~ Protected/Override Methods
    // ==============================================================================

    @SuppressWarnings("unchecked")
    protected Class<V> getVOClass() {
        return (Class<V>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    @SuppressWarnings("unchecked")
    protected Class<R> getROClass() {
        return (Class<R>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }
}
