package com.tellus.support;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端
 *
 * @author Roy
 * @date 2020/5/19 11:46
 */
@Data
@Builder
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = -7637540076936393300L;

    /**
     * 是否为移动平
     */
    private final boolean mobile;
    /**
     * 请求Ip
     */
    private final String ipAddress;
    /**
     * 域名
     */
    private final String domain;
    /**
     * 请求url
     */
    private final String url;
    /**
     * 浏览器类型
     */
    private final String browser;
    /**
     * 系统类型
     */
    private final String os;
    /**
     * 平台类型
     */
    private final String platform;
    /**
     * 引擎类型
     */
    private final String engine;
    /**
     * 浏览器版本
     */
    private final String version;
    /**
     * 引擎版本
     */
    private final String engineVersion;
}
