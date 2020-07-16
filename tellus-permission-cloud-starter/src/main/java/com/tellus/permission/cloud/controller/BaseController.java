package com.tellus.permission.cloud.controller;

import com.tellus.config.dozer.DozerGenerator;
import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.support.enums.UserTypeEnum;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Objects;

import static com.tellus.support.constants.SystemConstants.ROLE_SUPPER_ADMIN;

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

    public Boolean isSupperAdmin(Serializable roleId) {
        return UserDetailsUtils.obtainUserDetails().getRoles()
                .stream()
                .filter(role -> Objects.equals(roleId, role.getId()))
                .anyMatch(role -> Objects.equals(ROLE_SUPPER_ADMIN, role.getRole()));
    }

    public Boolean isInsider() {
        return UserDetailsUtils.obtainUserType() == UserTypeEnum.INSIDER;
    }

}
