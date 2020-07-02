package com.tellus.permission.oauth2.configuration;

import com.tellus.permission.oauth2.TellusSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置
 *
 * @author Roy
 * @date 2020/7/1 23:22
 */
@Order(2)
@EnableWebSecurity
@EnableConfigurationProperties(TellusSecurityProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


}
