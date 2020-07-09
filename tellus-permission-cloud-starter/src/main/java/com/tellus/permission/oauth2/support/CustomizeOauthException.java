package com.tellus.permission.oauth2.support;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.Serializable;

/**
 * 自定义: Oauth2 通用异常处理
 *
 * @author Roy
 * @date 2020/7/9 11:36
 */
@JsonSerialize(using = CustomizeOauthExceptionSerializer.class)
public class CustomizeOauthException extends OAuth2Exception implements Serializable {
    private static final long serialVersionUID = 8466478748717544305L;

    public CustomizeOauthException(String msg, Throwable t) {
        super(msg, t);
    }
}
