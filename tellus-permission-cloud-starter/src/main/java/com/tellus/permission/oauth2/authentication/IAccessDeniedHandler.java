package com.tellus.permission.oauth2.authentication;

import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.support.Result;
import com.tellus.toolkit.util.ResponseStatusUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决认证过的用户访问无权限访问资源的异常
 *
 * @author Roy
 * @date 2020/7/9 13:06
 */
@Slf4j
public class IAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {

        log.warn("No access to resources: {}, {}", request, accessDeniedException.getMessage());
        String resourceValue = UserDetailsUtils.obtainResourceOfRequest().getValue();
        String message = "无权限访问资源" + " [" + resourceValue + "]";

        ResponseStatusUtils.writer(response,
                Result.error(HttpStatus.FORBIDDEN.value(), message, request.getRequestURI()),
                HttpStatus.FORBIDDEN);
    }
}
