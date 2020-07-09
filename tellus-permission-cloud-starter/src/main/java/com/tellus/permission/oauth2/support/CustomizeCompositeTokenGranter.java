package com.tellus.permission.oauth2.support;

import com.google.common.collect.Lists;
import com.tellus.permission.oauth2.service.AuthorizationService;
import com.tellus.support.RequestInfo;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.toolkit.util.SpringRequestUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 重写 TOKEN 授予者, 登录成功后写入日志
 *
 * @author Roy
 * @date 2020/7/6 16:39
 */
public class CustomizeCompositeTokenGranter extends CompositeTokenGranter {

    private static final List<String> AUTHORIZED_GRANT_TYPES = Lists.newArrayList("authorization_code",
            "implicit", "password", "client_credentials");
    private final AuthorizationService authorizationService;

    public CustomizeCompositeTokenGranter(List<TokenGranter> tokenGranters,
                                          AuthorizationService authorizationService) {
        super(tokenGranters);
        this.authorizationService = authorizationService;
    }

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        OAuth2AccessToken token = super.grant(grantType, tokenRequest);

        if (null != token && AUTHORIZED_GRANT_TYPES.contains(grantType)) {
            String username = tokenRequest.getRequestParameters().get("username");
            RequestInfo requestInfo = SpringRequestUtils.getRequestInfo();
            this.authorizationService.loginSuccessAfter(
                    CreateLoginLogVO
                            .builder()
                            .username(username)
                            .loginTime(LocalDateTime.now())
                            .clientHost(requestInfo.getDomain())
                            .clientMac(null)
                            .device(requestInfo.getOs())
                            .userAgent(requestInfo.getBrowser())
                            .referer(requestInfo.getDomain())
                            .successful(Boolean.TRUE)
                            .build());
        }

        return token;
    }
}


