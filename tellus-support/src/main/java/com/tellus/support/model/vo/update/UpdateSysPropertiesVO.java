package com.tellus.support.model.vo.update;

import com.tellus.support.annotation.IPrimary;
import com.tellus.support.annotation.IUniqueness;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创建系统常量配置VO
 *
 * @author Roy
 * @date 2020/7/1 0:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询系统常量配置VO")
public class UpdateSysPropertiesVO implements Serializable {
    private static final long serialVersionUID = 1712539946624632990L;

    @IPrimary
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @IUniqueness(message = "常量KEY已存在 {%s}")
    @ApiModelProperty(value = "常量KEY", example = "SYS_NAME")
    private String key;

    @ApiModelProperty(value = "常量值", example = "system")
    private String value;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

    @ApiModelProperty(value = "更新人", example = "system")
    private String updater;

    @ApiModelProperty(value = "更新时间", example = "2020-07-01 00:00:00")
    private LocalDateTime updatedAt;

}
