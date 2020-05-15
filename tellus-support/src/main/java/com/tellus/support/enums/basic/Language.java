package com.tellus.support.enums.basic;

import lombok.Getter;

/**
 * description TODO
 *
 * @author Roy
 * @date 2020/5/15 22:38
 */
@Getter
public enum Language {

    /**
     * 中文简体
     */
    ZH_CN(1),
    /**
     * 中文繁体
     */
    ZH_TW(2),
    /**
     * US
     */
    EN_US(3);

    private final Integer code;

    Language(Integer code) {
        this.code = code;
    }
}
