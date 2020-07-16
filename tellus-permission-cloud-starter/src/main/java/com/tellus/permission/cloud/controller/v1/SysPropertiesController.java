package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractCrudController;
import com.tellus.permission.cloud.service.ISysPropertiesService;
import com.tellus.support.model.vo.create.CreateSysPropertiesVO;
import com.tellus.support.model.vo.result.SysPropertiesVO;
import com.tellus.support.model.vo.retrieve.RetrieveSysPropertiesVO;
import com.tellus.support.model.vo.update.UpdateSysPropertiesVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统常量配置管理
 *
 * @author Roy
 * @date 2020/7/16 22:02
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/sys/properties")
@Api(tags = "系统常量配置管理")
public class SysPropertiesController extends AbstractCrudController<SysPropertiesVO,
        CreateSysPropertiesVO, RetrieveSysPropertiesVO, UpdateSysPropertiesVO> {
    // ~ Static fields/initializers
    // ==============================================================================

    private final ISysPropertiesService sysPropertiesService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public SysPropertiesController(ISysPropertiesService sysPropertiesService) {
        super(sysPropertiesService);
        this.sysPropertiesService = sysPropertiesService;
    }

    // ~ Main Methods
    // ==============================================================================


    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================


    // ~ Private Methods
    // ==============================================================================

}
