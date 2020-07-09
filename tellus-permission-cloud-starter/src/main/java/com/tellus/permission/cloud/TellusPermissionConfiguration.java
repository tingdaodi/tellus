package com.tellus.permission.cloud;

import com.tellus.config.userinfo.UserInfoArgumentResolver;
import com.tellus.config.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Tellus permission cloud starter 启用依赖
 *
 * @author Roy
 * @date 2020/7/9 18:12
 */
@Configuration
@ComponentScan(basePackages = {"com.tellus.permission"})
@EnableConfigurationProperties(TellusPermissionProperties.class)
public class TellusPermissionConfiguration implements WebMvcConfigurer {

    private final UserInfoService userInfoService;

    @Autowired
    public TellusPermissionConfiguration(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserInfoArgumentResolver(userInfoService));
    }
}
