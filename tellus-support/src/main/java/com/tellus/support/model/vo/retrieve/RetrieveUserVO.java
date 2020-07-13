package com.tellus.support.model.vo.retrieve;

import com.tellus.support.annotation.IQueries;
import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.FactorType;
import com.tellus.support.enums.basic.Gender;
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
 * 查询用户VO
 *
 * @author Roy
 * @date 2020/7/13 12:31
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询用户VO")
public class RetrieveUserVO implements Serializable {
    private static final long serialVersionUID = 8487804198972165684L;

    @ApiModelProperty(value = "ID", example = "1000")
    private Integer id;

    @IQueries(value = "username", type = FactorType.STRING, option = OptionType.LIKE)
    @ApiModelProperty(value = "用户名 (%LIKE%)", example = "user001")
    private String username;

    @IQueries(value = "nickname", type = FactorType.STRING, option = OptionType.LIKE)
    @ApiModelProperty(value = "用户名 (%LIKE%)", example = "user001")
    private String nickname;

    @ApiModelProperty(value = "性别", example = "1-男,2-女", allowableValues = "1,2")
    private Gender gender;

    @ApiModelProperty(value = "用户类型", example = "1-内部用户，2-外部用户", allowableValues = "1,2")
    private UserTypeEnum userType;

    @ApiModelProperty(value = "状态", example = "1-正常，2-禁用", allowableValues = "1,2")
    private UserStatusEnum status;

    @ApiModelProperty(value = "注册IP地址 (LIKE%)", example = "192.168.0.1")
    private String ipAddress;

    @IQueries(value = "lastLoginTime", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "最后登录时间-开始时间", example = "2020-07-13 00:00:00")
    private LocalDateTime beginLastLoginTime;

    @IQueries(value = "lastLoginTime", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "最后登录时间-结束时间", example = "2020-07-13 00:00:00")
    private LocalDateTime endLastLoginTime;

    @IQueries(value = "lastLoginIp", type = FactorType.STRING, option = OptionType.LIKE_LEFT)
    @ApiModelProperty(value = "最后登录ID地址", example = "192.168.0.1")
    private String lastLoginIp;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "创建时间-开始时间", example = "2020-07-13 00:00:00")
    private LocalDateTime beginCreatedAt;

    @IQueries(value = "createdAt", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "创建时间-结束时间", example = "2020-07-13 00:00:00")
    private LocalDateTime endCreatedAt;

    @ApiModelProperty(value = "更新人", example = "system")
    private String updater;

    @IQueries(value = "updatedAt", type = FactorType.DATE, option = OptionType.BETWEEN)
    @ApiModelProperty(value = "更新时间-开始时间", example = "2020-07-13 00:00:00")
    private LocalDateTime beginUpdatedAt;

    @IQueries(value = "updatedAt", type = FactorType.DATE, option = OptionType.AND)
    @ApiModelProperty(value = "更新时间-结束时间", example = "2020-07-13 00:00:00")
    private LocalDateTime endUpdatedAt;

}
