package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.UserEntity;
import com.tellus.support.model.cohesive.UserDetailsCondenser;

import java.util.List;

/**
 * <p>
 * 用户表 用户信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface UserService extends ICustomizeService<UserEntity> {

    /**
     * 根据用户名, 查询用户详细信息 (仅用于授权认证)
     *
     * @param username 用户名
     * @return UserDetailsCondenser
     */
    UserDetailsCondenser findUserDetails(String username);

    /**
     * 根据组织 Id 查询用户
     *
     * @param groupIds 组织 Ids
     * @return List
     */
    List<UserEntity> findByGroupIds(List<Integer> groupIds);

}
