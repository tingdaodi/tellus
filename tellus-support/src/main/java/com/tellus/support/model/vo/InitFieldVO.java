package com.tellus.support.model.vo;

import com.tellus.support.enums.ParamMethodEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Init: 初始化字段VO
 *
 * @author Roy
 * @date 2020/7/9 22:12
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "初始化字段VO")
public class InitFieldVO implements Serializable {
    private static final long serialVersionUID = -461651030977530230L;

    @NotEmpty(message = "所属资源的值不能为空")
    @ApiModelProperty(value = "所属资源的值", example = "/fields GET")
    private String resourceValue;

    @NotNull(message = "参数方式不能为空")
    @ApiModelProperty(value = "参数方式", example = "1-入参，2-回参", allowableValues = "1,2")
    private ParamMethodEnum method;

    @NotEmpty(message = "参数标签不能为空")
    @ApiModelProperty(value = "参数标签", example = "用户名")
    private String label;

    @NotEmpty(message = "参数名称不能为空")
    @ApiModelProperty(value = "参数名称", example = "username")
    private String name;

    @NotEmpty(message = "参数类型不能为空")
    @ApiModelProperty(value = "参数类型", example = "String")
    private String type;

    @NotNull(message = "是否启用不能为空")
    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "备注", example = "允许输入的最大长度20")
    private String remark;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InitFieldVO)) {
            return false;
        }

        InitFieldVO that = (InitFieldVO) o;
        return Objects.equals(resourceValue, that.resourceValue)
                && method == that.method
                && Objects.equals(name, that.name)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceValue, method, method, name, type);
    }
}
