package com.tellus.web.service;

import com.tellus.service.core.model.LoginLogEntity;

/**
 * 认证/授权, 通用服务接口
 *
 * @author Roy
 * @date 2020/6/28 15:24
 */
public interface SystemAuthService {

    boolean loginSuccessAfter(LoginLogEntity entity);

}
