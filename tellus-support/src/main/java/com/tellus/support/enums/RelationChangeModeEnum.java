package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 关系变更模式：1-节点，2-分支
 *
 * @author Roy
 * @date 2020/5/17 16:21
 */
@Getter
@ApiModel(value = "RelationChangeMode", description = "变更模式")
public enum RelationChangeModeEnum {

    /**
     * 节点
     */
    @ISerializedName(value = "1")
    NODE(1),
    /**
     * 分支
     */
    @ISerializedName(value = "2")
    BRANCH(2),
    ;

    @EnumValue
    private final Integer code;

    RelationChangeModeEnum(Integer code) {
        this.code = code;
    }
}
