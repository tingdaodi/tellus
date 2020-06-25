package com.tellus.config.userinfo;

import com.tellus.support.UserInfo;
import com.tellus.toolkit.ExceptionUtils;

import java.util.Optional;

/**
 * 获取用户信息接口 (APi-微服务之间的通信)
 *
 * @author Roy
 * @date 2020/6/23 13:40
 * @see com.tellus.support.annotation.GetRequestInfo
 * @see com.tellus.support.UserInfo
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return Optional<UserInfo>
     */
    default Optional<UserInfo> getUserInfo(String username) {
        throw ExceptionUtils.mpe("Interface not implemented");
    }

}
