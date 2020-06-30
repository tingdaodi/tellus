package com.tellus.support.model.vo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统常量配置VO
 *
 * @author Roy
 * @date 2020/7/1 0:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "系统常量配置VO")
public class SysPropertiesVO implements Serializable {
    private static final long serialVersionUID = 1712539946624632990L;

    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @ApiModelProperty(value = "常量KEY", example = "SYS_NAME")
    private String key;

    @ApiModelProperty(value = "常量值", example = "system")
    private String value;

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

}
