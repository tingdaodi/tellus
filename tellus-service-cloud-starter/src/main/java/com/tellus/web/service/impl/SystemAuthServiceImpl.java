package com.tellus.web.service.impl;

import com.tellus.config.dozer.DozerGenerator;
import com.tellus.service.core.model.LoginLogEntity;
import com.tellus.service.core.model.UserEntity;
import com.tellus.service.core.service.single.LoginLogService;
import com.tellus.service.core.service.single.UserService;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.web.service.SystemAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 认证/授权, 通用服务接口
 *
 * @author Roy
 * @date 2020/7/1 18:55
 */
@Service
public class SystemAuthServiceImpl implements SystemAuthService {

    // ~ Static fields/initializers
    // ==============================================================================

    private final LoginLogService loginLogService;
    private final UserService userService;
    private final DozerGenerator dozerGenerator;

    // ~ Contracts
    // ==============================================================================

    @Autowired
    public SystemAuthServiceImpl(LoginLogService loginLogService,
                                 UserService userService,
                                 DozerGenerator dozerGenerator) {
        this.loginLogService = loginLogService;
        this.userService = userService;
        this.dozerGenerator = dozerGenerator;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void loginSuccessAfter(CreateLoginLogVO createLoginLogVO) {
        //  更新用户信息
        this.userService.lambdaUpdate().ge(UserEntity::getUsername, createLoginLogVO.getUsername())
                .set(UserEntity::getLastLoginIp, createLoginLogVO.getClientIp())
                .set(UserEntity::getLastLoginTime, createLoginLogVO.getLoginTime())
                .set(UserEntity::getUpdater, createLoginLogVO.getUsername())
                .set(UserEntity::getUpdatedAt, LocalDateTime.now())
                .update();

        //  保存登录日志
        this.loginLogService.save(dozerGenerator.convert(createLoginLogVO, LoginLogEntity.class));
    }
}
