package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 字段显示模式
 *
 * @author Roy
 * @date 2020/5/15 19:27
 */
@Getter
@ApiModel(value = "DisplayMode", description = "字段显示模式")
public enum DisplayModeEnum {

    /**
     * 正常显示
     */
    @ISerializedName(value = "1")
    NORMAL(1),
    /**
     * 部分显示
     */
    @ISerializedName(value = "2")
    PARTIAL(2),
    /**
     * 隐藏
     */
    @ISerializedName(value = "3")
    HIDDEN(3),
    ;

    @EnumValue
    private final Integer code;

    DisplayModeEnum(Integer code) {
        this.code = code;
    }
}
