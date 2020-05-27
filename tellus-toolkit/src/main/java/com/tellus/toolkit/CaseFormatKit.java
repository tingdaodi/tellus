package com.tellus.toolkit;

import com.google.common.base.CaseFormat;

import java.util.regex.Pattern;

/**
 * 大小写格式之间转换
 *
 * @author Roy
 * @date 2020/5/26 0:19
 */
public class CaseFormatKit {

    /**
     * 判断是否为大写+下划线的格式: CREATED_AT
     */
    private static final Pattern UPPER_UNDERSCORE = Pattern.compile("^(?<!_)[A-Z0-9_]*(?<!_)$");

    /**
     * e.g., "UPPER_UNDERSCORE"
     *
     * @param src 值
     * @return String
     */
    public static String toUpperUnderscore(String src) {
        if (!UPPER_UNDERSCORE.matcher(src).matches()) {
            src = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, src);
        }
        return src;
    }
}
