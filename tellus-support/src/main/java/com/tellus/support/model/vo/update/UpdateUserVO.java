package com.tellus.support.model.vo.update;

import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新用户VO
 *
 * @author Roy
 * @date 2020/7/13 13:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "更新用户VO")
public class UpdateUserVO implements Serializable {
    private static final long serialVersionUID = -8080468473756269273L;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @ApiModelProperty(value = "用户名 (%LIKE%)", example = "user001")
    private String nickname;

    @ApiModelProperty(value = "性别", example = "1-男,2-女", allowableValues = "1,2")
    private Gender gender;

    @ApiModelProperty(value = "用户类型", example = "1-内部用户，2-外部用户", allowableValues = "1,2")
    private UserTypeEnum userType;

    @ApiModelProperty(value = "状态", example = "1-正常，2-禁用", allowableValues = "1,2")
    private UserStatusEnum status;

}
