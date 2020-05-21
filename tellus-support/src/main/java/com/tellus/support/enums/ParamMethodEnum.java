package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 出入参数
 *
 * @author Roy
 * @date 2020/5/15 23:04
 */
@Getter
@ApiModel(value = "ParamMethod", description = "出/入参")
public enum ParamMethodEnum {

    /**
     * 入参
     */
    @ISerializedName(value = "1")
    INCOMING(1),
    /**
     * 回参
     */
    @ISerializedName(value = "2")
    RETURN(2),
    ;

    @EnumValue
    private final Integer code;

    ParamMethodEnum(Integer code) {
        this.code = code;
    }
}
