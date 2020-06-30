package com.tellus.support.model.vo.update;

import com.tellus.support.annotation.IPrimary;
import com.tellus.support.annotation.IUniqueness;
import com.tellus.support.enums.OperationTypeEnum;
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
 * 更新资源VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "更新资源VO")
public class UpdateResourceVO implements Serializable {

    private static final long serialVersionUID = -2230922397269304302L;

    @IPrimary
    @NotNull(message = "资源ID不能为空")
    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "资源名称", example = "查询用户")
    private String name;

    @IUniqueness(message = "资源的值已存在 {%s}", operationType = OperationTypeEnum.UPDATED)
    @ApiModelProperty(value = "资源的值", example = "/users")
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
