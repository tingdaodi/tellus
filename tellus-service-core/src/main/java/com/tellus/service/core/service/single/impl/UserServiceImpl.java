package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.UserMapper;
import com.tellus.service.core.model.UserEntity;
import com.tellus.service.core.service.single.UserService;
import com.tellus.support.model.cohesive.UserDetailsCondenser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 用户信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class UserServiceImpl extends ICustomizeServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserDetailsCondenser findUserDetails(String username) {
        return baseMapper.selectUserDetails(username);
    }

    @Override
    public List<UserEntity> findByGroupIds(List<Integer> groupIds) {
        return baseMapper.selectByGroupIds(groupIds);
    }
}
