package com.tellus.permission.oauth2;

import com.google.common.collect.Sets;
import com.tellus.support.annotation.Nullable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * Spring Security and oauth2 配置参数
 *
 * @author Roy
 * @date 2020/7/1 23:22
 */
@Data
@ConfigurationProperties(prefix = "tellus.security")
public class TellusSecurityProperties {

    @Value("${spring.mvc.servlet.path:}")
    private String servletPath;

    private String loginProcessingUrl = "/login";
    private String logoutUrl = "/logout";
    /**
     * 忽略权限认证的 URL
     */
    private String ignoredUrls = "/error,/js/**,/css/**,/img/**,/images/**,/fronts/**,/**/favicon.ico";

    /**
     * 是否授予未配置的资源访问权限
     * <p>
     * true: 登录后即可访问
     * false: 拒绝访问
     */
    private Boolean nonConfiguredResourcesAccessGrantedEnable = Boolean.FALSE;

    /**
     * 认证即可访问的资源
     * <p>
     * e.g. /users/{1} GET
     */
    private Set<String> authenticatedAccessibleResources;

    /**
     * 资源服务ID
     */
    private String resourceId = "tellus-resources";

    /**
     * 客户端ID
     */
    private String clientId = "tellus-cloud";

    /**
     * 客户端密码: 123456
     */
    private String clientSecret = "$2a$10$asVumbS904yuLVl5lJ.AG.wlVKAwUcnzsRGSheYXMmzgmnEW/kzw2";

    /**
     * 作用域 (CRUD), 默认: all
     * 用户限制客户端的访问范围
     */
    private Set<String> scopes = Sets.newHashSet("all");

    /**
     * 授权服务支持的资源服务ID
     */
    private Set<String> resourceIds = Sets.newHashSet("tellus-resources");

    /**
     * 客户端可以使用的授权类型
     * <p>
     * authorization_code: 授权码类型
     * implicit: 隐式授权类型
     * password: 资源所有者密码类型
     * client_credentials: 客户端凭据( 客户端ID及key )类型
     */
    private Set<String> authorizedGrantTypes = Sets.newHashSet("password", "refresh_token");

    /**
     * 授予客户端的权限 (常规 Spring Security 权限)
     */
    private Set<String> authorities = Sets.newHashSet("oauth2");

    /**
     * 访问 Token 有效期, 默认: 30 min
     */
    private Duration accessTokenValidity = Duration.ofMillis(30);

    /**
     * 刷新 Token 有效期, 默认: 30 min
     */
    private Duration refreshTokenValidity = Duration.ofMillis(30);

    /**
     * 认证头部参数
     */
    private String tokenHeader = "Authorization";

    /**
     * Token 前缀
     */
    private String tokenPrefix = "bearer";

    /**
     * 附加信息
     */
    @Nullable
    private Map<String, Object> additionalInformation;

    @Nullable
    private Set<String> autoApproveScopes;

}
