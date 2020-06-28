package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.UserEntity;
import com.tellus.support.model.cohesive.UserDetailsCondenser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 用户信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据用户名, 查询用户详细信息 (仅用于授权认证)
     *
     * @param username 用户名
     * @return UserDetailsCondenser
     */
    UserDetailsCondenser selectUserDetails(@Param("username") String username);

    /**
     * 根据组织 Id 查询用户
     *
     * @param groupIds 组织 Ids
     * @return List
     */
    List<UserEntity> selectByGroupIds(@Param("groupIds") List<Integer> groupIds);

}
