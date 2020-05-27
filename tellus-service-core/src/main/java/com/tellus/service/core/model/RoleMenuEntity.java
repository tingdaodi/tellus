package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表 角色与菜单关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_role_menu")
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID 角色ID
     */
    @TableId("ROLE_ID")
    private Integer roleId;

    /**
     * 菜单ID 菜单ID
     */
    @TableField("MENU_ID")
    private Integer menuId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                "}";
    }
}
