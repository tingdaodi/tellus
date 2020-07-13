package com.tellus.permission.cloud.service;

import com.tellus.support.model.cohesive.UserDetailsCondenser;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.support.model.vo.update.RevisePasswordVO;
import com.tellus.support.model.vo.update.ReviseUserGroupVO;
import com.tellus.support.model.vo.update.ReviseUserPlatformVO;
import com.tellus.support.model.vo.update.ReviseUserRoleVO;

/**
 * 授权/认证通用接口
 *
 * @author Roy
 * @date 2020/7/13 13:22
 */
public interface ISystemAuthService {

    /**
     * 登录成功后, 保存登录日志
     *
     * @param loginLogVO VO
     * @return Boolean
     */
    Boolean loginSuccessAfter(CreateLoginLogVO loginLogVO);

    /**
     * 更新/重置密码
     *
     * @param revisePasswordVO VO
     * @return Boolean
     */
    Boolean revisePassword(RevisePasswordVO revisePasswordVO);

    /**
     * 认证使用, 查询用户信息 (组织, 角色, 平台, 密码等)
     *
     * @param username 用户名
     * @return UserDetailsCondenser
     */
    UserDetailsCondenser findUserDetailByUsername(String username);

    /**
     * 修改用户的角色
     *
     * @param reviseUserRoleVO VO
     * @return Boolean
     */
    Boolean reviseRoles(ReviseUserRoleVO reviseUserRoleVO);

    /**
     * 修改用户的平台
     *
     * @param reviseUserPlatformVO VO
     * @return Boolean
     */
    Boolean revisePlatforms(ReviseUserPlatformVO reviseUserPlatformVO);

    /**
     * 修改用户的组织
     *
     * @param reviseUserGroupVO VO
     * @return Boolean
     */
    Boolean reviseGroup(ReviseUserGroupVO reviseUserGroupVO);

}
