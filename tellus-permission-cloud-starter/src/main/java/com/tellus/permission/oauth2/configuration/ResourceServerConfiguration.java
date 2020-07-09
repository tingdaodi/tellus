package com.tellus.permission.oauth2.configuration;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.tellus.permission.oauth2.TellusSecurityProperties;
import com.tellus.permission.oauth2.authentication.*;
import com.tellus.permission.oauth2.authorization.IFilterInvocationSecurityMetadataSource;
import com.tellus.permission.oauth2.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Arrays;
import java.util.List;

/**
 * 资源服务配置
 *
 * @author Roy
 * @date 2020/7/9 12:52
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final WebMvcProperties webMvcProperties;
    private final TellusSecurityProperties tellusSecurityProperties;
    private final AuthorizationService authorizationService;
    private final ConsumerTokenServices consumerTokenServices;

    @Autowired
    public ResourceServerConfiguration(WebMvcProperties webMvcProperties,
                                       TellusSecurityProperties tellusSecurityProperties,
                                       AuthorizationService authorizationService,
                                       ConsumerTokenServices consumerTokenServices) {
        this.webMvcProperties = webMvcProperties;
        this.tellusSecurityProperties = tellusSecurityProperties;
        this.authorizationService = authorizationService;
        this.consumerTokenServices = consumerTokenServices;
    }

    @Bean
    public IFilterInvocationSecurityMetadataSource supperMetadataSource() {
        return new IFilterInvocationSecurityMetadataSource(webMvcProperties, authorizationService);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());

        AccessDecisionVoter<?> accessDecisionVoter = new IAccessDecisionVoter(
                tellusSecurityProperties.getNonConfiguredResourcesAccessGrantedEnable(),
                tellusSecurityProperties.getAuthenticatedAccessibleResources());

        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(new RoleVoter(), webExpressionVoter,
                accessDecisionVoter, new AuthenticatedVoter());

        UnanimousBased unanimousBased = new UnanimousBased(decisionVoters);
        unanimousBased.setAllowIfAllAbstainDecisions(true);

        return unanimousBased;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new IAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new IAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new IAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new IAuthenticationSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ILogoutSuccessHandler(tellusSecurityProperties, consumerTokenServices);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(tellusSecurityProperties.getResourceId())
                .stateless(true)
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] ignoredUrls = Iterables.toArray(
                Splitter.on(",").trimResults().omitEmptyStrings()
                        .split(tellusSecurityProperties.getIgnoredUrls()), String.class);

        http
                .cors().disable()
                .headers().frameOptions().disable()

                .and()
                .logout().logoutUrl(tellusSecurityProperties.getLogoutUrl())
                .logoutSuccessHandler(logoutSuccessHandler())
                .clearAuthentication(true)
                .permitAll()

                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())

                .and()
                .authorizeRequests()
                .antMatchers(ignoredUrls)
                .permitAll()

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        IFilterInvocationSecurityMetadataSource metadataSource = supperMetadataSource();
                        metadataSource.setSupperMetadataSource(fsi.getSecurityMetadataSource());
                        fsi.setSecurityMetadataSource(metadataSource);
                        return fsi;
                    }
                })

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());
    }
}
