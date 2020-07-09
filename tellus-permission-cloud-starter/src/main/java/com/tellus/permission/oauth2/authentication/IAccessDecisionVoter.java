package com.tellus.permission.oauth2.authentication;

import com.tellus.toolkit.ResourceKit;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义: 投票器
 *
 * @author Roy
 * @date 2020/7/9 12:54
 */
public class IAccessDecisionVoter implements AccessDecisionVoter<Object> {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final Boolean nonConfiguredResourceAccessGrantedEnable;
    private final Set<String> authenticatedAccessibleResources;

    public IAccessDecisionVoter(Boolean nonConfiguredResourceAccessGrantedEnable,
                                Set<String> authenticatedAccessibleResources) {
        this.nonConfiguredResourceAccessGrantedEnable = nonConfiguredResourceAccessGrantedEnable;
        this.authenticatedAccessibleResources = authenticatedAccessibleResources;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return null != attribute && ResourceKit.supported(attribute.getAttribute());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        //  否决
        if (null == authentication) {
            return ACCESS_DENIED;
        }

        //  当前用户已有的资源权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        int result = ACCESS_ABSTAIN;
        for (ConfigAttribute attribute : attributes) {
            //  1. 启用认证即可访问资源
            if (nonConfiguredResourceAccessGrantedEnable) {
                if (null == authenticatedAccessibleResources || authenticatedAccessibleResources.isEmpty()) {
                    return ACCESS_ABSTAIN;
                }

                for (String resource : authenticatedAccessibleResources) {
                    if (antPathMatcher.match(ResourceKit.builderPermission(resource), attribute.getAttribute())) {
                        return ACCESS_ABSTAIN;
                    }
                }
            }

            //  2. 未启用, 且未授权访问
            else if (null == attribute.getAttribute()) {
                return ACCESS_DENIED;
            }

            //  3. 权限校验
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;
                for (GrantedAuthority authority : authorities) {
                    if (antPathMatcher.match(attribute.getAttribute(), authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }

}
