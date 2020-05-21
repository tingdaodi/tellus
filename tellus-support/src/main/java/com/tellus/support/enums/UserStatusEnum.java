package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author Roy
 * @date 2020/5/17 16:55
 */
@Getter
@ApiModel(value = "UserStatus", description = "用户状态")
public enum UserStatusEnum {

    /**
     * 禁用
     */
    @ISerializedName(value = "1")
    DISABLE(1),

    /**
     * 正常
     */
    @ISerializedName(value = "2")
    NORMAL(2),

    /**
     * 已初始化
     */
    @ISerializedName(value = "3")
    INITIALIZED(3);

    @EnumValue
    private final Integer code;

    UserStatusEnum(Integer code) {
        this.code = code;
    }
}
