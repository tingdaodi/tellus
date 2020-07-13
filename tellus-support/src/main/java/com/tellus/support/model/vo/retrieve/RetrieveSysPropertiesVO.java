package com.tellus.support.model.vo.retrieve;

import com.tellus.support.annotation.IQueries;
import com.tellus.support.enums.basic.FactorType;
import com.tellus.support.enums.basic.OptionType;
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
 * 创建系统常量配置VO
 *
 * @author Roy
 * @date 2020/7/1 0:16
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询系统常量配置VO")
public class RetrieveSysPropertiesVO implements Serializable {
    private static final long serialVersionUID = 1712539946624632990L;

    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @ApiModelProperty(value = "常量KEY", example = "SYS_NAME")
    private String key;

    @NotEmpty(message = "常量值不能为空")
    @ApiModelProperty(value = "常量值", example = "system")
    private String value;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "创建时间-开始时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdBeginTime;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "创建时间-结束时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdEndTime;

}
