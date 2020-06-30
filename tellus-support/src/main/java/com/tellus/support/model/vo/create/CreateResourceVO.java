package com.tellus.support.model.vo.create;

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
 * 创建资源VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建资源VO")
public class CreateResourceVO implements Serializable {

    private static final long serialVersionUID = 363931531134998894L;

    @NotEmpty(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称", example = "查询用户")
    private String name;

    @IUniqueness(message = "资源的值已存在 {%s}")
    @NotEmpty(message = "资源的值不能为空")
    @ApiModelProperty(value = "资源的值", example = "/users")
    private String value;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

}
