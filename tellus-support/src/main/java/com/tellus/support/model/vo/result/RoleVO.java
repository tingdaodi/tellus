package com.tellus.support.model.vo.result;

import com.tellus.support.annotation.IExpose;
import com.tellus.support.interfaces.ISubordinate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色VO")
public class RoleVO implements ISubordinate, Serializable {

    private static final long serialVersionUID = 4704783417690296193L;

    @IExpose
    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "角色名称", example = "超级管理员")
    private String name;

    @ApiModelProperty(value = "角色编号", example = "ADMIN")
    private String role;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新人", example = "system")
    private String updater;

    @ApiModelProperty(value = "更新时间", example = "2020-07-01 00:00:00")
    private LocalDateTime updatedAt;

    /**
     * 层级关系
     */
    @ApiModelProperty(value = "直属上级 Id", example = "1000")
    private Integer parentId;
}
