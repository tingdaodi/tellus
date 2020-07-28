package com.tellus.permission.cloud.model.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 重置密码VO
 *
 * @author Roy
 * @date 2020/7/28 10:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "重置密码VO")
public class ResetApiPasswordVO implements Serializable {
    private static final long serialVersionUID = 7891856444453307203L;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, example = "user001")
    private String username;

    @NotEmpty(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码", required = true, example = "123456")
    private String newPassword;
}
