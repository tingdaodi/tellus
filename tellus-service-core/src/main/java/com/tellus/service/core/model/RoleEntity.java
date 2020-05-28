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
 * 角色表 角色信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 角色名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 角色标识
     */
    @TableField("ROLE")
    private String role;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 是否启用 0-未启用，1-启用
     */
    @TableField("ENABLED")
    private Integer enabled;

    /**
     * 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 更新人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 更新时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

}
