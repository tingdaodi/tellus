package com.tellus.support.model.vo.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改密码VO
 *
 * @author Roy
 * @date 2020/7/13 13:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "修改密码VO")
public class RevisePasswordVO implements Serializable {
    private static final long serialVersionUID = 7155548379453537511L;

    @ApiModelProperty(value = "用户名", required = true, example = "user001")
    private String username;

    @ApiModelProperty(value = "新密码", required = true, example = "ag866!@#$")
    private String newPassword;

}
