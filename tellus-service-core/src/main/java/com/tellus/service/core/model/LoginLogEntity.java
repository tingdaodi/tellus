package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 登录日志表 记录登录日志
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_login_log")
public class LoginLogEntity implements Serializable {

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
     * 登录时间 登录时间
     */
    @TableField("LOGIN_TIME")
    private String loginTime;

    /**
     * 客户端IP/HOST 客户端IP/HOST
     */
    @TableField("CLIENT_HOST")
    private String clientHost;

    /**
     * 客户端MAC 客户端MAC
     */
    @TableField("CLIENT_MAC")
    private String clientMac;

    /**
     * 登录设备 登录设备
     */
    @TableField("DEVICE")
    private String device;

    /**
     * 代理信息 代理信息(浏览器头信息)
     */
    @TableField("USER_AGENT")
    private String userAgent;

    /**
     * 来源地址 来源地址
     */
    @TableField("REFERER")
    private String referer;

    /**
     * 是否成功 是否成功
     */
    @TableField("SICCESSFUL")
    private Integer siccessful;


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

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public Integer getSiccessful() {
        return siccessful;
    }

    public void setSiccessful(Integer siccessful) {
        this.siccessful = siccessful;
    }

    @Override
    public String toString() {
        return "LoginLogEntity{" +
                "id=" + id +
                ", username=" + username +
                ", loginTime=" + loginTime +
                ", clientHost=" + clientHost +
                ", clientMac=" + clientMac +
                ", device=" + device +
                ", userAgent=" + userAgent +
                ", referer=" + referer +
                ", siccessful=" + siccessful +
                "}";
    }
}
