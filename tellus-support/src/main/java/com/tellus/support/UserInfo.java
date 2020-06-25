package com.tellus.support;

import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.Gender;

import java.io.Serializable;

/**
 * 用户基本信息
 *
 * @author Roy
 * @date 2020/6/25 11:03
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2492562751689266823L;

    /**
     * 主键 主键
     */
    private Integer id;

    /**
     * 用户名 用户名
     */
    private String username;

    /**
     * 昵称 昵称
     */
    private String nickname;

    /**
     * 性别 1-男，2-女；默认：1
     */
    private Gender gender;

    /**
     * 用户类型 1-内部用户，2-外部用户；默认：1
     */
    private UserTypeEnum userType;

    /**
     * 状态 1-正常，2-禁用；默认：1
     */
    private UserStatusEnum status;

    /**
     * 头像 头像
     */
    private String avatar;
}
