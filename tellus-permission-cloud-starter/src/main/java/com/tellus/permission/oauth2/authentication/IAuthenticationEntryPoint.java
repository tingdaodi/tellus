package com.tellus.permission.oauth2.authentication;

import com.tellus.support.Result;
import com.tellus.toolkit.util.ResponseStatusUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决匿名访问无权限资源的异常处理
 *
 * @author Roy
 * @date 2020/7/9 13:24
 */
@Slf4j
public class IAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {
        log.warn("No access to resources(Anonymous): {}, {}", request, authException.getMessage());
        ResponseStatusUtils.writer(response,
                Result.error(HttpStatus.UNAUTHORIZED.value(), "No access to resource", request.getRequestURI()),
                HttpStatus.UNAUTHORIZED);
    }
}
