package com.tellus.crud.controller;

import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

/**
 * 查询地城通用 controller
 *
 * @author Roy
 * @date 2020/5/28 12:58
 */
@Validated
public abstract class AbstractUpdateController<T, U> extends AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================


    // ~ Request Methods
    // ==============================================================================

    @IOperationLog(theme = "更新记录", type = OperationTypeEnum.UPDATED, operator = "#{#username}")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新记录", notes = "更新一条记录 Restful API")
    public Boolean update(@SuppressWarnings("unused") @RequestHeader("username") String username,
                          @Valid @RequestBody U updatedVO) {
        //  校验数据唯一性
        checkUniqueness(updatedVO);
        T entity = this.convert(updatedVO, getEntityClass());
        return this.customizeService.updateById(entity);
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
