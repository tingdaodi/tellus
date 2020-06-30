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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询平台VO
 *
 * @author Roy
 * @date 2020/6/30 22:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询平台VO")
public class RetrievePlatformVO implements Serializable {

    private static final long serialVersionUID = 4704783417690296193L;

    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "平台名称", example = "平台A")
    private String name;

    @ApiModelProperty(value = "平台编号", example = "A01")
    private String code;

    @ApiModelProperty(value = "是否启用", example = "0-未启用，1-启用", allowableValues = "0,1")
    private Boolean enabled;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "创建时间-开始时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdBeginTime;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdEndTime;

}
