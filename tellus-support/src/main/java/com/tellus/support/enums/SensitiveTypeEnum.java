package com.tellus.support.enums;

import com.tellus.support.interfaces.ISensitiveType;
import lombok.Getter;

import java.util.EnumSet;

/**
 * 敏感数据加密
 *
 * @author Roy
 * @date 2020/5/17 17:45
 */
@Getter
public enum SensitiveTypeEnum implements ISensitiveType {

    /**
     * 通用
     */
    GENERAL("default", "A001"),
    /**
     * 名称
     */
    NAME("name", "A002"),
    /**
     * 电话号码
     */
    PHONE("phone", "A003"),
    /**
     * 邮箱
     */
    EMAIL("email", "A004"),
    ;

    private final String name;
    private final String keyPrefix;

    SensitiveTypeEnum(String name, String keyPrefix) {
        this.name = name;
        this.keyPrefix = keyPrefix;
    }

    public static SensitiveTypeEnum builder(String name) {
        return EnumSet.allOf(SensitiveTypeEnum.class)
                .stream()
                .filter(v -> null != name && name.equalsIgnoreCase(v.name))
                .findFirst()
                .orElse(SensitiveTypeEnum.GENERAL);
    }
}
