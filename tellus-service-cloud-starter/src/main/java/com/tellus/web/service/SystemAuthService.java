package com.tellus.web.service;

import com.tellus.support.model.vo.create.CreateLoginLogVO;

/**
 * 认证/授权, 通用服务接口
 *
 * @author Roy
 * @date 2020/6/28 15:24
 */
public interface SystemAuthService {

    /**
     * 登录成功后, 写入登录日志,
     *
     * @param createLoginLogVO VO
     * @return Boolean
     */
    void loginSuccessAfter(CreateLoginLogVO createLoginLogVO);

}
