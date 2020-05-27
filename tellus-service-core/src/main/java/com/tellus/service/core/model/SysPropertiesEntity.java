package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 系统配置表 系统运行依赖配置
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
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
    private String enabled;

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
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间 更新时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SysPropertiesEntity{" +
                "id=" + id +
                ", key=" + key +
                ", value=" + value +
                ", enabled=" + enabled +
                ", remark=" + remark +
                ", creator=" + creator +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
