package com.tellus.permission.oauth2.support;

import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.exception.NotMatchException;
import com.tellus.support.exception.TokenExpireException;
import com.tellus.toolkit.ResourceKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tellus.support.constants.SystemConstants.ROLE_SUPPER_ADMIN;

/**
 * 获取 Security 用户信息
 *
 * @author Roy
 * @date 2020/7/2 11:00
 */
@Slf4j
public class UserDetailsUtils {

    // ~ Static fields/initializers
    // ==============================================================================

    /**
     * 路由匹配规则
     */
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    // ~ Static Basic Methods
    // ==============================================================================

    /**
     * @return 用户ID
     */
    public static Integer obtainUserId() {
        return obtainUserDetails().getDetails().getId();
    }

    /**
     * @return 用户名
     */
    public static String obtainUsername() {
        return obtainUserDetails().getDetails().getUsername();
    }

    /**
     * @return 用户状态
     */
    public static UserStatusEnum obtainStatus() {
        return obtainUserDetails().getDetails().getStatus();
    }

    /**
     * @return 用户类型
     */
    public static UserTypeEnum obtainUserType() {
        return obtainUserDetails().getDetails().getUserType();
    }

    /**
     * @return 权限
     */
    public static Collection<? extends GrantedAuthority> obtainAuthorities() {
        return obtainUserDetails().getAuthorities();
    }

    /**
     * @return 用户详细信息
     */
    public static CustomizeUserDetails.Details obtainDetails() {
        return obtainUserDetails().getDetails();
    }

    /**
     * @return 身份认证信息
     */
    public static CustomizeUserDetails obtainUserDetails() {
        try {
            return (CustomizeUserDetails) obtainAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new TokenExpireException("Obtain UserDetails, Token has expired, " + e.getMessage(), e);
        }
    }

    /**
     * @return 认证主体
     */
    public static Authentication obtainAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * @return 是否为超级管理员
     */
    public static Boolean isSupperAdmin() {
        return obtainUserDetails().getRoles().stream()
                .anyMatch(role -> role.getRole().equals(ROLE_SUPPER_ADMIN));
    }

    // ~ Static of Request
    // ==============================================================================

    /**
     * @return 当前 URL 对应的字段信息
     */
    public static CustomizeUserDetails.Resource obtainResourceOfRequest() {
        String resourceValue = ResourceKit.builderValue();
        return obtainUserDetails().getResources()
                .stream()
                .filter(resource -> ANT_PATH_MATCHER.match(resource.getValue(), resourceValue))
                .findFirst()
                .orElseThrow(NotMatchException::new);
    }

    /**
     * @return 当前 URL 对应的资源信息
     */
    public static Set<CustomizeUserDetails.Field> obtainFieldOfRequest() {
        String resourceValue = ResourceKit.builderValue();
        return obtainUserDetails().getFields()
                .stream()
                .filter(field -> Objects.equals(field.getResourceId(), obtainResourceOfRequest().getId()))
                .collect(Collectors.toSet());
    }


}
