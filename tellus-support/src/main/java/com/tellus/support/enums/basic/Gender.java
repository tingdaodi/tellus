package com.tellus.support.enums.basic;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 性别
 *
 * @author Roy
 * @date 2020/5/15 22:38
 */
@Getter
@ApiModel(value = "Gender", description = "性别")
public enum Gender {

    /**
     * 男性
     */
    MALE(1, "男"),
    ;

    private final Integer code;
    private final String desc;

    Gender(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
