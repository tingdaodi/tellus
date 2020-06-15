package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 用户平台表 用户与平台关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-09
 */
@TableName("t_user_platform")
public class UserPlatformEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID 用户ID
     */
    @TableField("USER_ID")
    private Integer userId;

    /**
     * 平台ID 平台ID
     */
    @TableField("PLATFORM_ID")
    private Integer platformId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    @Override
    public String toString() {
        return "UserPlatformEntity{" +
                "userId=" + userId +
                ", platformId=" + platformId +
                "}";
    }
}
