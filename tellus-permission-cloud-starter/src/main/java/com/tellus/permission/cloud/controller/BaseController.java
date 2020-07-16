package com.tellus.permission.cloud.controller;

import com.tellus.config.dozer.DozerGenerator;
import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.support.enums.UserTypeEnum;

import javax.annotation.Resource;

/**
 * 通用 controller , 提供底层通用方式实现
 *
 * @author Roy
 * @date 2020/7/13 16:59
 */
public abstract class BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    @Resource
    private DozerGenerator dozerGenerator;

    // ~ Methods
    // ==============================================================================

    public Boolean isSupperAdmin() {
        return UserDetailsUtils.isSupperAdmin();
    }

    public Boolean isInsider() {
        return UserDetailsUtils.obtainUserType() == UserTypeEnum.INSIDER;
    }

}
