package com.tellus.crud.controller;

import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

/**
 * 创建底层通用 controller
 *
 * @author Roy
 * @date 2020/5/28 12:27
 */
@Validated
public abstract class AbstractCreateController<T, C> extends AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================


    // ~ Common Methods
    // ==============================================================================

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

    // ~ Override/Protected Methods
    // ==============================================================================


    // ~ Private Methods
    // ==============================================================================

}
