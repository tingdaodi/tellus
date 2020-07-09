package com.tellus.permission.oauth2.configuration;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.tellus.permission.oauth2.service.AuthorizationService;
import com.tellus.permission.oauth2.support.CustomizeCompositeTokenGranter;
import com.tellus.permission.oauth2.support.CustomizeResponseExceptionTranslator;
import com.tellus.permission.oauth2.support.CustomizeSingleTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.util.Collections;

/**
 * 授权服务配置
 *
 * @author Roy
 * @date 2020/7/6 15:14
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private RedisTokenStore redisTokenStore;

    private final WebMvcProperties webMvcProperties;
    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService;
    private final UserDetailsService userDetailsService;
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationServerConfiguration(WebMvcProperties webMvcProperties,
                                            AuthenticationManager authenticationManager,
                                            ClientDetailsService clientDetailsService,
                                            UserDetailsService userDetailsService,
                                            AuthorizationService authorizationService) {
        this.webMvcProperties = webMvcProperties;
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.userDetailsService = userDetailsService;
        this.authorizationService = authorizationService;
    }

    @Bean
    public TokenStore tokenStore() {
        if (null == redisTokenStore) {
            return new InMemoryTokenStore();
        }
        return redisTokenStore;
    }

    @SuppressWarnings("rawtypes")
    @Bean
    WebResponseExceptionTranslator customizeWebResponseExceptionTranslator() {
        return new CustomizeResponseExceptionTranslator();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //  允许表单验证
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        String servletPath = webMvcProperties.getServlet().getPath();
        if (!Strings.isNullOrEmpty(servletPath)) {
            endpoints.prefix(servletPath);
        }

        //noinspection unchecked
        endpoints
                .tokenStore(tokenStore())
                .tokenServices(tokenServices(endpoints))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .exceptionTranslator(customizeWebResponseExceptionTranslator())
                //  允许 GET, POST 请求获取 token, 即访问端点: /oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        //  重写 Token 授予者, 认证成功后后流程处理
        endpoints.tokenGranter(new CustomizeCompositeTokenGranter(
                Lists.newArrayList(endpoints.getTokenGranter()), authorizationService));
    }

    private CustomizeSingleTokenServices tokenServices(AuthorizationServerEndpointsConfigurer endpoints) {
        CustomizeSingleTokenServices tokenServices = new CustomizeSingleTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        addUserDetailsService(tokenServices, this.userDetailsService);

        return tokenServices;
    }

    private void addUserDetailsService(CustomizeSingleTokenServices tokenServices,
                                       UserDetailsService userDetailsService) {
        if (null != userDetailsService) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
            tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }

    @Autowired(required = false)
    public void setRedisTokenStore(RedisTokenStore redisTokenStore) {
        this.redisTokenStore = redisTokenStore;
    }
}
