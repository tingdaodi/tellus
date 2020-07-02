package com.tellus.permission.oauth2.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

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


}
