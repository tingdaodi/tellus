package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractCrudController;
import com.tellus.permission.cloud.service.IResourceService;
import com.tellus.support.model.vo.create.CreateResourceVO;
import com.tellus.support.model.vo.result.ResourceVO;
import com.tellus.support.model.vo.retrieve.RetrieveResourceVO;
import com.tellus.support.model.vo.update.UpdateResourceVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源管理
 *
 * @author Roy
 * @date 2020/7/16 20:43
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/resources")
@Api(tags = "资源管理")
public class ResourceController extends AbstractCrudController<ResourceVO,
        CreateResourceVO, RetrieveResourceVO, UpdateResourceVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IResourceService resourceService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public ResourceController(IResourceService resourceService) {
        super(resourceService);
        this.resourceService = resourceService;
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
