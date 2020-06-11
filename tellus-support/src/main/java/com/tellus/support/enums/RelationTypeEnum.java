package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 关系类型：1-用户，2-部门，3-角色，4-资源
 *
 * @author Roy
 * @date 2020/5/17 16:23
 */
@Getter
@ApiModel(value = "RelationType", description = "关系类型")
public enum RelationTypeEnum {

    /**
     * 用户
     */
    @ISerializedName(value = "1")
    USER(1),
    /**
     * 部门
     */
    @ISerializedName(value = "2")
    GROUP(2),
    /**
     * 角色
     */
    @ISerializedName(value = "3")
    ROLE(3),
    /**
     * 资源
     */
    @ISerializedName(value = "4")
    RESOURCE(4),
    /**
     * 菜单
     */
    @ISerializedName(value = "5")
    MENU(5),
    ;

    @EnumValue
    private final Integer code;

    RelationTypeEnum(Integer code) {
        this.code = code;
    }
}
