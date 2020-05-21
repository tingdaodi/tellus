package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tellus.support.annotation.ISerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

/**
 * 操作类型
 *
 * @author Roy
 * @date 2020/5/15 18:22
 */
@Getter
@ApiModel(value = "OperationType", description = "操作类型")
public enum OperationTypeEnum {
    /**
     * 读取
     */
    @ISerializedName(value = "1")
    RETRIEVED(1),
    /**
     * 更新
     */
    @ISerializedName(value = "2")
    UPDATED(2),
    /**
     * 创建
     */
    @ISerializedName(value = "3")
    CREATED(3),
    /**
     * 删除
     */
    @ISerializedName(value = "4")
    DELETED(4),
    /**
     * 逻辑删除
     */
    @ISerializedName(value = "5")
    LOGIC_DELETED(5),
    /**
     * 导出
     */
    @ISerializedName(value = "6")
    EXPORTED(6),
    /**
     * 导入
     */
    @ISerializedName(value = "7")
    IMPORTED(7),
    /**
     * 审批
     */
    @ISerializedName(value = "8")
    APPROVED(8),
    ;

    @EnumValue
    private final Integer code;

    OperationTypeEnum(Integer code) {
        this.code = code;
    }

    public static Optional<OperationTypeEnum> builder(Integer code) {
        return EnumSet
                .allOf(OperationTypeEnum.class)
                .stream()
                .filter(v -> v.code.equals(code))
                .findFirst();
    }
}
