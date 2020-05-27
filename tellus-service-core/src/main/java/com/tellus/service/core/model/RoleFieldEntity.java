package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 角色字段表 角色与字段关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_role_field")
public class RoleFieldEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID 角色ID
     */
    @TableId("ROLE_ID")
    private Integer roleId;

    /**
     * 字段ID 字段ID
     */
    @TableField("FIELD_ID")
    private Integer fieldId;

    /**
     * 显示模式 默认：1；1-全显示，2-半显示，3-隐藏
     */
    @TableField("DISPLAY_MODE")
    private Integer displayMode;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(Integer displayMode) {
        this.displayMode = displayMode;
    }

    @Override
    public String toString() {
        return "RoleFieldEntity{" +
                "roleId=" + roleId +
                ", fieldId=" + fieldId +
                ", displayMode=" + displayMode +
                "}";
    }
}
