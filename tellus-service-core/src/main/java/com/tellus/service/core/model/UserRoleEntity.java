package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表 用户与角色信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_user_role")
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID 用户ID
     */
    @TableId("USER_ID")
    private Integer userId;

    /**
     * 角色ID 角色ID
     */
    @TableField("ROLE_ID")
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}
