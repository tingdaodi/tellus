package com.tellus.config.requestinfo;

import com.tellus.support.annotation.IRequestInfo;
import com.tellus.toolkit.util.RequestUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动注入客户端信息
 *
 * @author Roy
 * @date 2020/5/19 12:37
 * @see com.tellus.support.annotation.IRequestInfo
 * @see com.tellus.support.RequestInfo
 */
public class RequestInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(IRequestInfo.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        return RequestUtils.getRequestInfo(request);
    }
}
