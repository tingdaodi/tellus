package com.tellus.permission.oauth2.support;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.Serializable;

/**
 * 自定义: Oauth2 通用异常处理
 *
 * @author Roy
 * @date 2020/7/9 11:36
 */
public class CustomizeOauthException extends OAuth2Exception implements Serializable {
    public CustomizeOauthException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomizeOauthException(String msg) {
        super(msg);
    }
}
