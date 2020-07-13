package com.tellus.permission.oauth2.configuration;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.tellus.permission.oauth2.TellusSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private final WebMvcProperties webMvcProperties;
    private final TellusSecurityProperties tellusSecurityProperties;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfiguration(WebMvcProperties webMvcProperties,
                                    TellusSecurityProperties tellusSecurityProperties,
                                    UserDetailsService userDetailsService) {
        this.webMvcProperties = webMvcProperties;
        this.tellusSecurityProperties = tellusSecurityProperties;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        String[] ignoredUrls = Iterables.toArray(
                Splitter.on(",").trimResults().omitEmptyStrings()
                        .split(tellusSecurityProperties.getIgnoredUrls()), String.class);

        web.ignoring().antMatchers(ignoredUrls);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String servletPath = webMvcProperties.getServlet().getPath();
        http.
                requestMatchers()
                .antMatchers(servletPath + "/oauth/**")

                .and()
                .authorizeRequests()
                .antMatchers(servletPath + "/oauth/**").authenticated()

                .and()
                .cors().disable();
    }
}
