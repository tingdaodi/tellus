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
 * 修改密码VO
 *
 * @author Roy
 * @date 2020/7/28 9:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "修改密码VO")
public class ReviseApiPasswordVO implements Serializable {
    private static final long serialVersionUID = 2273521752135042162L;

    @NotEmpty(message = "原密码不能为空")
    @ApiModelProperty(value = "原密码", required = true, example = "123456")
    private String originalPassword;

    @NotEmpty(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码", required = true, example = "123456")
    private String newPassword;

}
