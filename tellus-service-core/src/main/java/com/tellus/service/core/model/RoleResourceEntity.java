package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 角色资源表 角色与资源关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_role_resource")
public class RoleResourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID 角色ID
     */
    @TableId("ROLE_ID")
    private Integer roleId;

    /**
     * 资源ID 资源ID
     */
    @TableField("RESOURCE_ID")
    private Integer resourceId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResourceEntity{" +
                "roleId=" + roleId +
                ", resourceId=" + resourceId +
                "}";
    }
}
