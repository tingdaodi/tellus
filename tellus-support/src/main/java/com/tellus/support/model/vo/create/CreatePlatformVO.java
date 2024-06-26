package com.tellus.support.model.vo.create;

import com.tellus.support.annotation.IExpose;
import com.tellus.support.annotation.IUniqueness;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创建平台VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建平台VO")
public class CreatePlatformVO implements Serializable {

    private static final long serialVersionUID = 8221128954568675575L;

    @NotEmpty(message = "平台名称不能为空")
    @ApiModelProperty(value = "平台名称", example = "平台A")
    private String name;

    @IUniqueness(message = "平台编号已存在 {%s}")
    @NotEmpty(message = "平台编号不能为空")
    @ApiModelProperty(value = "平台编号", example = "A01")
    private String code;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

}
