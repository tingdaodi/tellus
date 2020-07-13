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
 * 查询登录日志VO
 *
 * @author Roy
 * @date 2020/6/30 12:01
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询登录日志VO")
public class RetrieveLoginLogVO implements Serializable {
    private static final long serialVersionUID = 6609481315946835707L;

    @ApiModelProperty(value = "ID", example = "10000")
    private Integer id;

    @ApiModelProperty(value = "用户名", example = "user001")
    private String username;

    @IQueries(value = "loginTime", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "登录时间-开始时间", example = "2020-07-01 00:00:00")
    private LocalDateTime loginBeginTime;

    @IQueries(value = "loginTime", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "登录时间-结束时间", example = "2020-07-01 00:01:00")
    private LocalDateTime loginEndTime;

    @ApiModelProperty(value = "客户端IP", example = "127.0.0.1")
    private String clientIp;

    @ApiModelProperty(value = "客户端HOST", example = "localhost")
    private String clientHost;

    @ApiModelProperty(value = "登录设备", example = "iOS")
    private String device;

    @ApiModelProperty(value = "代理信息", example = "Chrome...")
    private String userAgent;

    @ApiModelProperty(value = "来源地址", example = "www.baidu.com")
    private String referer;

    @ApiModelProperty(value = "是否成功", example = "0-失败, 1-成功", allowableValues = "0,1")
    private Integer successful;
}
