package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录日志表 记录登录日志
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime loginTime;

    /**
     * 客户端IP
     */
    @TableField("CLIENT_IP")
    private String clientIp;

    /**
     * 客户端HOST
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
    @TableField("SUCCESSFUL")
    private Boolean successful;

}
