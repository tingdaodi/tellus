package com.tellus.permission.oauth2.authentication;

import com.google.common.base.Strings;
import com.tellus.permission.oauth2.TellusSecurityProperties;
import com.tellus.support.Result;
import com.tellus.toolkit.util.ResponseStatusUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销成功处理器
 *
 * @author Roy
 * @date 2020/7/9 13:37
 */
@Slf4j
@Setter
public class ILogoutSuccessHandler implements LogoutSuccessHandler {

    private TellusSecurityProperties tellusSecurityProperties;
    private ConsumerTokenServices consumerTokenServices;

    public ILogoutSuccessHandler(TellusSecurityProperties tellusSecurityProperties,
                                 ConsumerTokenServices consumerTokenServices) {
        this.tellusSecurityProperties = tellusSecurityProperties;
        this.consumerTokenServices = consumerTokenServices;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) {
        String authHeader = request.getHeader(tellusSecurityProperties.getTokenHeader());
        String tokenPrefix = tellusSecurityProperties.getTokenPrefix();
        if (!Strings.isNullOrEmpty(authHeader) && authHeader.startsWith(tokenPrefix)) {
            final String xToken = authHeader.substring(tokenPrefix.length() + 1);
            consumerTokenServices.revokeToken(xToken);
        }

        log.info("Logout successful, {}", request);
        ResponseStatusUtils.writer(response, Result.error("Logout successful"));
    }
}
