package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 组织角色表
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-06
 */
@TableName("t_group_role")
public class GroupRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织ID 组织ID
     */
    @TableField("GROUP_ID")
    private Integer groupId;

    /**
     * 角色ID 角色ID
     */
    @TableField("ROLE_ID")
    private Integer roleId;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "GroupRoleEntity{" +
                "groupId=" + groupId +
                ", roleId=" + roleId +
                "}";
    }
}
