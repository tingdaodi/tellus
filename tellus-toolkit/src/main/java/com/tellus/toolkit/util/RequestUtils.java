package com.tellus.toolkit.util;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import com.tellus.support.RequestInfo;
import com.tellus.toolkit.CharsKit;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求信息处理工具
 *
 * @author Roy
 * @date 2020/5/19 12:40
 */
public class RequestUtils {

    private static final String[] PROXY_REMOTE_IP_ADDRESS_HEADERS = {"X-Real-IP", "x-forwarded-for",
            "Proxy-client-IP", "WL-Proxy-Client-IP"};

    public static RequestInfo getRequestInfo(HttpServletRequest request) {
        String curIp = getIpAddress(request);
        String userAgentString = request.getHeader(HttpHeaders.USER_AGENT);
        UserAgent userAgent = UserAgentUtil.parse(userAgentString);

        return RequestInfo.builder()
                .mobile(userAgent.isMobile())
                .ipAddress(curIp)
                .domain(userAgent.getBrowser().getName())
                .url(request.getRequestURL().toString())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOs().getName())
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .version(userAgent.getVersion())
                .engineVersion(userAgent.getEngineVersion())
                .build();
    }

    public static String getIpAddress(HttpServletRequest request) {
        for (String headerKey : PROXY_REMOTE_IP_ADDRESS_HEADERS) {
            String header = request.getHeader(headerKey);

            if (!Strings.isNullOrEmpty(header)
                    && !CharsKit.UNKNOWN.equalsIgnoreCase(header)) {
                String[] strings = header.split(CharsKit.COMMA);
                for (String string : strings) {
                    String value = string.trim();
                    if (!Strings.isNullOrEmpty(value)
                            && !CharsKit.UNDERLINE.equalsIgnoreCase(value)) {
                        return value;
                    }
                }
            }
        }
        return request.getRemoteAddr().trim();
    }
}
