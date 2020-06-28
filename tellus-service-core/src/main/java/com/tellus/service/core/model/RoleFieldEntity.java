package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.tellus.support.enums.DisplayModeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 角色字段表 角色与字段关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private DisplayModeEnum displayMode;

}
