package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户表 用户信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 用户名 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码 密码密文
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 昵称 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

    /**
     * 性别 1-男，2-女；默认：1
     */
    @TableField("GENDER")
    private Integer gender;

    /**
     * 用户类型 1-内部用户，2-外部用户；默认：1
     */
    @TableField("USER_TYPE")
    private Integer userType;

    /**
     * 状态 1-正常，2-禁用；默认：1
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 邮箱 邮箱地址
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 联系电话 联系电话
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 办公电话 办公电话
     */
    @TableField("OFFICE_PHONE")
    private String officePhone;

    /**
     * 出生日期 出生日期
     */
    @TableField("BIRTHDAY")
    private LocalDateTime birthday;

    /**
     * 头像 头像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 使用语言 使用语言
     */
    @TableField("LANGUAGE")
    private Integer language;

    /**
     * 注册IP地址 注册地址
     */
    @TableField("IP_ADDRESS")
    private String ipAddress;

    /**
     * 最后登录时间 最后登录时间
     */
    @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录ID地址 最后登录IP地址
     */
    @TableField("LAST_LOGIN_IP")
    private String lastLoginIp;

    /**
     * 备注 备注
     */
    @TableField("REMARKS")
    private String remarks;

    /**
     * 创建人 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 创建时间 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 更新人 更新人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 更新时间 更新时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除 0-未删除，1-已删除；默认：0
     */
    @TableField("DELETED")
    private Integer deleted;

}
