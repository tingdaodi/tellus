package com.tellus.permission.oauth2.authentication;

import com.tellus.support.Result;
import com.tellus.toolkit.util.ResponseStatusUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功后处理器
 *
 * @author Roy
 * @date 2020/7/9 13:35
 */
@Slf4j
public class IAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        ResponseStatusUtils.writer(response, Result.success("Login successful"));
    }
}
