package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractCrudController;
import com.tellus.permission.cloud.service.ISystemUserService;
import com.tellus.support.model.vo.create.CreateUserVO;
import com.tellus.support.model.vo.result.UserVO;
import com.tellus.support.model.vo.retrieve.RetrieveUserVO;
import com.tellus.support.model.vo.update.UpdateUserVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author Roy
 * @date 2020/7/24 14:43
 */
@Slf4j
@Validated
@RequestMapping(value = "/users")
@RestController
@Api(tags = "(系统)用户管理")
public class SystemUserController extends AbstractCrudController<UserVO, CreateUserVO, RetrieveUserVO, UpdateUserVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final ISystemUserService systemUserService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public SystemUserController(ISystemUserService systemUserService) {
        super(systemUserService);
        this.systemUserService = systemUserService;
    }

    // ~ Methods
    // ==============================================================================

}
