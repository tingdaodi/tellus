package com.tellus.support.enums;

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
    RETRIEVED(1),
    /**
     * 更新
     */
    UPDATED(2),
    /**
     * 创建
     */
    CREATED(3),
    /**
     * 删除
     */
    DELETED(4),
    /**
     * 逻辑删除
     */
    LOGIC_DELETED(5),
    /**
     * 导出
     */
    EXPORTED(6),
    /**
     * 导入
     */
    IMPORTED(7),
    /**
     * 审批
     */
    APPROVED(8),
    ;

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
