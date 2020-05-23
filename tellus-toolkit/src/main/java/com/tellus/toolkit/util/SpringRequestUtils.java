package com.tellus.toolkit.util;

import com.tellus.support.RequestInfo;
import com.tellus.toolkit.util.RequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Spring Request 工具类
 *
 * @author Roy
 * @date 2020/5/21 13:53
 */
public class SpringRequestUtils {

    public static String getIpAddress() {
        return RequestUtils.getIpAddress(getRequest());
    }

    public static RequestInfo getRequestInfo() {
        return RequestUtils.getRequestInfo(getRequest());
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getRequest();
    }


}
