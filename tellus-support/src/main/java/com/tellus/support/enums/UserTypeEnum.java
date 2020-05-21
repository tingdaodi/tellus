package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author Roy
 * @date 2020/5/17 16:59
 */
@Getter
@ApiModel(value = "UserType", description = "用户类型")
public enum UserTypeEnum {

    /**
     * 内部用户
     */
    @ISerializedName(value = "1")
    INSIDER(1),

    /**
     * 外部用户
     */
    @ISerializedName(value = "2")
    OUTSIDER(2),
    ;

    @EnumValue
    private final Integer code;

    UserTypeEnum(Integer code) {
        this.code = code;
    }
}
