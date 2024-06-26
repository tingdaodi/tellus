package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractRetrieveController;
import com.tellus.permission.cloud.service.ILoginLogService;
import com.tellus.support.model.vo.result.LoginLogVO;
import com.tellus.support.model.vo.retrieve.RetrieveLoginLogVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志管理
 *
 * @author Roy
 * @date 2020/7/16 18:21
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/login/logs")
@Api(tags = "登录日志管理")
public class LoginLogController extends AbstractRetrieveController<LoginLogVO, RetrieveLoginLogVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final ILoginLogService loginLogService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public LoginLogController(ILoginLogService loginLogService) {
        super(loginLogService);
        this.loginLogService = loginLogService;
    }

    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================


    // ~ Private Methods
    // ==============================================================================

}
