package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 组织用户表 组织与用户关系信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
