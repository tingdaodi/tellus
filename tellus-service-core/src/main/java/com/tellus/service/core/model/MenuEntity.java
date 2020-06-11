package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 菜单表 菜单表
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_menu")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 菜单名称 菜单名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 菜单的值 菜单的值
     */
    @TableField("VALUE")
    private String value;

    /**
     * 是否启用 0-未启用，1-已启用；默认：1
     */
    @TableField("ENABLED")
    private Integer enabled;

    /**
     * 备注 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 排序 排序
     */
    @TableField("SORT")
    private Integer sort;

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
     * 直属上级 Id
     */
    @TableField(exist = false)
    private Integer parentId;
}
