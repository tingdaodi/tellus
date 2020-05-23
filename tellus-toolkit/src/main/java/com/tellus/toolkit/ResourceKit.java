package com.tellus.toolkit;

import com.tellus.toolkit.util.SpringRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * 资源工具
 *
 * @author Roy
 * @date 2020/5/23 14:17
 */
public class ResourceKit {

    /**
     * 权限格式: /users GET -> :users::GET
     */
    private static final String PERMISSION_STRING = "^:.*::(GET|POST|PUT|DELETE)$";
    private static final Pattern PERMISSION = Pattern.compile(PERMISSION_STRING);

    public static String replaceValue(String value, String replace) {
        String[] values = value.split(StringPool.SPACE);
        if (values.length == 2) {
            return ResourceKit.builderValue(values[0],
                    values[1].startsWith(replace) ? values[1] : replace.concat(values[1]));
        }
        return value;
    }

    public static String builderValue() {
        HttpServletRequest request = SpringRequestUtils.getRequest();
        return ResourceKit.builderValue(request.getMethod(), request.getRequestURI());
    }

    public static String builderValue(String method, String url) {
        return method.concat(StringPool.SPACE).concat(url);
    }

    public static String builderPermission(String value) {
        String doubleColon = "::";
        if (value.contains(StringPool.SLASH)) {
            return value.replaceAll(StringPool.SLASH, StringPool.COLON).replaceAll(StringPool.SPACE, doubleColon);
        }
        return value;
    }

    public static Boolean supported(String value) {
        return null != value && PERMISSION.matcher(value).matches();
    }

}
