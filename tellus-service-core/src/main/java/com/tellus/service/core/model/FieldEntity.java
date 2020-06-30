package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tellus.support.enums.DisplayModeEnum;
import com.tellus.support.enums.ParamMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字段表 字段信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_field")
public class FieldEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 所属资源ID 所属资源ID
     */
    @TableField("RESOURCE_ID")
    private Integer resourceId;

    /**
     * 参数方式: 1-入参，2-回参
     */
    @TableField("METHOD")
    private ParamMethodEnum method;

    /**
     * 参数标签 参数标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 参数名称 参数名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 参数类型 参数类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 是否启用 0-未启用，1-启用
     */
    @TableField("ENABLED")
    private Boolean enabled;

    /**
     * 备注 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 创建人 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 创建时间 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 更新人 更新人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 更新时间 更新时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

    /**
     * 字段显示模式, 仅在根据角色查询时
     */
    @TableField(exist = false)
    private DisplayModeEnum displayMode;

}
