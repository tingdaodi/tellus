package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户平台表 用户与平台关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
