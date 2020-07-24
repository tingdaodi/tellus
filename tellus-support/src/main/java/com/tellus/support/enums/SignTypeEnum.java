package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import lombok.Getter;

/**
 * 登录日志类型: 1-登录, 2-登出
 *
 * @author Roy
 * @date 2020/7/24 15:00
 */
@Getter
public enum SignTypeEnum {

    /**
     * 登入
     */
    @ISerializedName("1")
    IN(1),
    /**
     * 登出
     */
    @ISerializedName("2")
    OUT(2),
    ;

    @EnumValue
    private final Integer code;

    SignTypeEnum(Integer code) {
        this.code = code;
    }
}
