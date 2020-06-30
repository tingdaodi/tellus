package com.tellus.support.model.vo.result;

import com.tellus.support.annotation.IExpose;
import com.tellus.support.enums.DisplayModeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字段VO
 *
 * @author Roy
 * @date 2020/6/30 17:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "字段VO")
public class FieldVO implements Serializable {
    private static final long serialVersionUID = 4498874066759255000L;

    @IExpose
    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @IExpose
    @ApiModelProperty(value = "所属资源ID", example = "10000")
    private Integer resourceId;

    @ApiModelProperty(value = "参数方式", example = "1-入参，2-回参", allowableValues = "{1,2}")
    private String method;

    @ApiModelProperty(value = "参数标签", example = "用户名")
    private String label;

    @ApiModelProperty(value = "参数名称", example = "username")
    private String name;

    @ApiModelProperty(value = "参数类型", example = "String")
    private String type;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "{0,1}")
    private String enabled;

    @ApiModelProperty(value = "备注", example = "允许输入的最大长度20")
    private String remark;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新人", example = "system")
    private String updater;

    @ApiModelProperty(value = "更新时间", example = "2020-07-01 00:00:00")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "字段显示模式", example = "1-全显示, 2-半显示, 3-隐藏", allowableValues = "{1,2,3}")
    private DisplayModeEnum displayMode;
}
