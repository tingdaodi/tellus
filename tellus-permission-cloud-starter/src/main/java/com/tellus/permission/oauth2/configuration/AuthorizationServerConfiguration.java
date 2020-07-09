package com.tellus.permission.oauth2.configuration;

import com.tellus.permission.oauth2.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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

    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService;
    private final UserDetailsService userDetailsService;
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationServerConfiguration(RedisTokenStore redisTokenStore,
                                            AuthenticationManager authenticationManager,
                                            ClientDetailsService clientDetailsService,
                                            UserDetailsService userDetailsService,
                                            AuthorizationService authorizationService) {
        this.redisTokenStore = redisTokenStore;
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

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }

    @Autowired(required = false)
    public void setRedisTokenStore(RedisTokenStore redisTokenStore) {
        this.redisTokenStore = redisTokenStore;
    }
}
