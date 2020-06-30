package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统配置表 系统运行依赖配置
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_properties")
public class SysPropertiesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 系统配置KEY 系统配置KEY
     */
    @TableField("KEY")
    private String key;

    /**
     * 系统配置值 系统配置值
     */
    @TableField("VALUE")
    private String value;

    /**
     * 是否启用 0-未启用，1-已启用
     */
    @TableField("ENABLED")
    private Boolean enabled;

    /**
     * 备注 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 创建人 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 创建时间 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 更新人 更新人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 更新时间 更新时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

}
