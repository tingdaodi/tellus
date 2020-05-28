package com.tellus.crud.controller;

import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 创建底层通用 controller: 根据主键删除数据
 *
 * @author Roy
 * @date 2020/5/28 12:49
 */
@Validated
public abstract class AbstractDeleteController<T> extends AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================


    // ~ Request Methods
    // ==============================================================================

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

    // ~ Override/Protected Methods
    // ==============================================================================

    @Override
    protected Class<T> getEntityClass() {
        //noinspection unchecked
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    // ~ Private Methods
    // ==============================================================================

}
