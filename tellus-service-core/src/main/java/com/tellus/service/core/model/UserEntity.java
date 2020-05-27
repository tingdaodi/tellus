package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 用户表 用户信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", nickname=" + nickname +
                ", gender=" + gender +
                ", userType=" + userType +
                ", status=" + status +
                ", email=" + email +
                ", phone=" + phone +
                ", officePhone=" + officePhone +
                ", birthday=" + birthday +
                ", avatar=" + avatar +
                ", language=" + language +
                ", ipAddress=" + ipAddress +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp=" + lastLoginIp +
                ", remarks=" + remarks +
                ", creator=" + creator +
                ", createdAt=" + createdAt +
                ", updater=" + updater +
                ", updatedAt=" + updatedAt +
                ", deleted=" + deleted +
                "}";
    }
}
