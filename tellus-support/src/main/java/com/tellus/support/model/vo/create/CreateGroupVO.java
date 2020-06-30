package com.tellus.support.model.vo.create;

import com.tellus.support.annotation.IExpose;
import com.tellus.support.annotation.IUniqueness;
import com.tellus.support.interfaces.IAncestor;
import com.tellus.support.interfaces.ISubordinate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 创建组织VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建组织VO")
public class CreateGroupVO implements IAncestor, Serializable {

    private static final long serialVersionUID = 363931531134998894L;

    @NotEmpty(message = "组织名称不能为空")
    @ApiModelProperty(value = "组织名称", example = "开发部")
    private String name;

    @IUniqueness(message = "组织编号已存在 {%s}")
    @NotEmpty(message = "组织编号不能为空")
    @ApiModelProperty(value = "组织编号", example = "DEV")
    private String code;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

    /**
     * 层级关系
     */
    @NotNull(message = "上级不能为空")
    @ApiModelProperty(value = "上级 Id", example = "1000")
    private Integer ancestor;
}
