package com.tellus.config.userinfo;

import com.google.common.base.Strings;
import com.tellus.support.annotation.GetUserInfo;
import com.tellus.support.exception.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动注入用户信息
 *
 * @author Roy
 * @date 2020/6/25 11:10
 */
@Slf4j
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String USERNAME_PARAM_NAME = "username";
    private UserInfoService userInfoService;

    public UserInfoArgumentResolver() {
    }

    public UserInfoArgumentResolver(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(GetUserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        GetUserInfo getUserInfo = parameter.getParameterAnnotation(GetUserInfo.class);

        if (null == getUserInfo || null == request) {
            return null;
        }

        String username = obtainHeaderUsername(request);
        if (getUserInfo.supported()) {
            return getUserInfoService().getUserInfo(username).orElseThrow(UsernameNotFoundException::new);
        }

        if (!Strings.isNullOrEmpty(username)) {
            return username;
        }

        return getUserInfo.username();
    }

    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private String obtainHeaderUsername(HttpServletRequest request) {
        return request.getHeader(USERNAME_PARAM_NAME);
    }
}
