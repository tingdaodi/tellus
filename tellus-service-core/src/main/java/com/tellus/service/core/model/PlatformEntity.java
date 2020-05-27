package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 平台信息表 系统支持多平台接入
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@TableName("t_platform")
public class PlatformEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 平台名称 平台名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 平台编号 平台编号
     */
    @TableField("CODE")
    private String code;

    /**
     * 是否启用 0-未启用，1-已启用
     */
    @TableField("ENABLED")
    private Integer enabled;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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
        return "PlatformEntity{" +
                "id=" + id +
                ", name=" + name +
                ", code=" + code +
                ", enabled=" + enabled +
                ", creator=" + creator +
                ", createdAt=" + createdAt +
                ", updater=" + updater +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
