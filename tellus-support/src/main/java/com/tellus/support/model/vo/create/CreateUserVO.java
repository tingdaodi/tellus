package com.tellus.support.model.vo.create;

import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.Gender;
import com.tellus.support.enums.basic.Language;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创建用户VO
 *
 * @author Roy
 * @date 2020/7/1 0:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建用户VO")
public class CreateUserVO implements Serializable {
    private static final long serialVersionUID = -4874761402507733853L;

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Gender gender;
    private UserTypeEnum userType;
    private UserStatusEnum status;
    private String email;
    private String phone;
    private String officePhone;
    private LocalDateTime birthday;
    private String avatar;
    private Language language;
    private String ipAddress;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String remarks;
    private String creator;
    private LocalDateTime createdAt;
    private String updater;
    private LocalDateTime updatedAt;

}
