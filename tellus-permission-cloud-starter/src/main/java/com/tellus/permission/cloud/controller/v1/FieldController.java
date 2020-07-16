package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractCrudController;
import com.tellus.permission.cloud.service.IFieldService;
import com.tellus.support.model.vo.create.CreateFieldVO;
import com.tellus.support.model.vo.result.FieldVO;
import com.tellus.support.model.vo.retrieve.RetrieveFieldVO;
import com.tellus.support.model.vo.update.UpdateFieldVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字段资源管理
 *
 * @author Roy
 * @date 2020/7/16 15:55
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/fields")
@Api(tags = "字段管理")
public class FieldController extends AbstractCrudController<FieldVO, CreateFieldVO, RetrieveFieldVO, UpdateFieldVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IFieldService fieldService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public FieldController(IFieldService fieldService, IFieldService fieldService1) {
        super(fieldService);
        this.fieldService = fieldService1;
    }

    // ~ Methods
    // ==============================================================================

    // TODO 或许可以实现数据权限校验

}
