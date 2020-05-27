package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 字段表 字段信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_field")
public class FieldEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 所属资源ID 所属资源ID
     */
    @TableField("RESOURCE_ID")
    private String resourceId;

    /**
     * 出/入参 1-入参，2-回参
     */
    @TableField("METHOD")
    private String method;

    /**
     * 参数标签 参数标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 参数名称 参数名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 参数类型 参数类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 是否启用 0-未启用，1-启用
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
    @TableField("UPDATER")
    private String updater;

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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", method=" + method +
                ", label=" + label +
                ", name=" + name +
                ", type=" + type +
                ", enabled=" + enabled +
                ", remark=" + remark +
                ", creator=" + creator +
                ", createdAt=" + createdAt +
                ", updater=" + updater +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
