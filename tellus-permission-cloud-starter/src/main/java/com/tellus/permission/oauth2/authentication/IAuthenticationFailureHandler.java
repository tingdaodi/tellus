package com.tellus.permission.oauth2.authentication;

import com.tellus.support.Result;
import com.tellus.toolkit.util.ResponseStatusUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败后处理器
 *
 * @author Roy
 * @date 2020/7/9 13:27
 */
@Slf4j
public class IAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        log.warn("Authentication Failure: {}, {}", request, exception.getMessage());
        ResponseStatusUtils.writer(response,
                Result.error("Login failed: username or password is incorrect", request.getRequestURI()),
                HttpStatus.UNAUTHORIZED);
    }
}
