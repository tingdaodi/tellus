package com.tellus.support.model.vo.retrieve;

import com.tellus.support.annotation.IQueries;
import com.tellus.support.enums.ParamMethodEnum;
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
 * 查询字段VO
 *
 * @author Roy
 * @date 2020/6/30 17:25
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询字段VO")
public class RetrieveFieldVO implements Serializable {

    private static final long serialVersionUID = 4406698726645980935L;

    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "所属资源ID", example = "10000")
    private Integer resourceId;

    @ApiModelProperty(value = "参数方式", example = "1-入参，2-回参", allowableValues = "1,2")
    private ParamMethodEnum method;

    @ApiModelProperty(value = "参数标签", example = "用户名")
    private String label;

    @ApiModelProperty(value = "参数名称", example = "username")
    private String name;

    @ApiModelProperty(value = "参数类型", example = "String")
    private String type;

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
