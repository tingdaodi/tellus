package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 组织用户表 组织与用户关系信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_group_user")
public class GroupUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织ID 组织ID
     */
    @TableId("GROUP_ID")
    private Integer groupId;

    /**
     * 用户ID 用户ID
     */
    @TableField("USER_ID")
    private Integer userId;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupUserEntity{" +
                "groupId=" + groupId +
                ", userId=" + userId +
                "}";
    }
}
