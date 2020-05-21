package com.tellus.config;

import com.tellus.config.requestinfo.RequestInfoArgumentResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 通用配置
 *
 * @author Roy
 * @date 2020/5/19 12:34
 */
@Configuration
@EnableConfigurationProperties(TellusGeneralProperties.class)
public class TellusGeneralConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestInfoArgumentResolver());
    }
}
