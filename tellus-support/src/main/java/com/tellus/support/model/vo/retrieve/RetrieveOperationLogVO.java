package com.tellus.support.model.vo.retrieve;

import com.tellus.support.annotation.IQueries;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.enums.RequestMethodEnum;
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
 * 查询操作日志记录VO
 *
 * @author Roy
 * @date 2020/7/10 17:07
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "读取操作日志记录VO")
public class RetrieveOperationLogVO implements Serializable {
    private static final long serialVersionUID = 4995398971811057388L;

    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "主题", example = "创建用户")
    private String theme;

    @ApiModelProperty(value = "操作人", example = "user001")
    private String operator;

    @ApiModelProperty(value = "操作类型", example = "1-查询，2-更新，3-新建，4-删除，5-逻辑删除", allowableValues = "1,2,3,4,5")
    private OperationTypeEnum operateType;

    @ApiModelProperty(value = "操作开始时间", example = "2020-07-10 00:00:00")
    private LocalDateTime operationBeginTime;

    @ApiModelProperty(value = "操作结束时间", example = "2020-07-10 00:00:00")
    private LocalDateTime operationEndTime;

    @ApiModelProperty(value = "是否成功", example = "0-失败，1-成功", allowableValues = "0,1")
    private Boolean successful;

    @IQueries(type = FactorType.STRING, option = OptionType.LIKE)
    @ApiModelProperty(value = "异常信息", example = "支持 LIKE 查询, e.g. %value%")
    private String exception;

    @IQueries(type = FactorType.STRING, option = OptionType.LIKE_LEFT)
    @ApiModelProperty(value = "客户端IP", example = "支持 LIKE 查询, e.g. value%")
    private String clientIp;

    @IQueries(type = FactorType.STRING, option = OptionType.LIKE_LEFT)
    @ApiModelProperty(value = "请求HOST", example = "支持 LIKE 查询, e.g. value%")
    private String requestHost;

    @IQueries(type = FactorType.STRING, option = OptionType.LIKE_LEFT)
    @ApiModelProperty(value = "请求URL", example = "支持 LIKE 查询, e.g. value%")
    private String requestUrl;

    @ApiModelProperty(value = "请求方式", example = "1", allowableValues = "1,2,3,4,5,6,7,8")
    private RequestMethodEnum requestMethod;

}
