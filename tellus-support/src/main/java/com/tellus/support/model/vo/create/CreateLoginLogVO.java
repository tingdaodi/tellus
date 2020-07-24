package com.tellus.support.model.vo.create;

import com.tellus.support.enums.SignTypeEnum;
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

/**
 * 创建登录日志VO
 *
 * @author Roy
 * @date 2020/6/29 9:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建登录日志VO")
public class CreateLoginLogVO implements Serializable {
    private static final long serialVersionUID = 6350369709775242156L;

    @NotEmpty(message = "登录类型不能为空")
    @ApiModelProperty(value = "登录类型", example = "1-登入, 2-登出", allowableValues = "1,2")
    private SignTypeEnum signType;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "user001")
    private String username;

    @NotNull(message = "登录时间不能为空")
    @ApiModelProperty(value = "登录时间", example = "2020-07-01 00:00:00")
    private LocalDateTime loginTime;

    @NotEmpty(message = "客户端IP不能为空")
    @ApiModelProperty(value = "客户端IP", example = "127.0.0.1")
    private String clientIp;

    @NotEmpty(message = "客户端HOST不能为空")
    @ApiModelProperty(value = "客户端HOST", example = "localhost")
    private String clientHost;

    @ApiModelProperty(value = "客户端MAC", example = "1:2:2:d:0:2")
    private String clientMac;

    @ApiModelProperty(value = "登录设备", example = "iOS")
    private String device;

    @ApiModelProperty(value = "代理信息", example = "Chrome...")
    private String userAgent;

    @ApiModelProperty(value = "来源地址", example = "www.baidu.com")
    private String referer;

    @ApiModelProperty(value = "是否成功", example = "0-失败, 1-成功", allowableValues = "0,1")
    private Boolean successful;

}
