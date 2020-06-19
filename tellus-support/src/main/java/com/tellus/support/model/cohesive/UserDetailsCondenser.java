package com.tellus.support.model.cohesive;

import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.Gender;
import com.tellus.support.enums.basic.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户聚合的 POJO
 *
 * @author Roy
 * @date 2020/6/16 1:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsCondenser implements Serializable {
    private static final long serialVersionUID = 5361178667181056491L;

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

    private List<GroupCohesive> groups;
    private List<RoleCohesive> roles;
    private List<PlatformCohesive> platforms;

}
