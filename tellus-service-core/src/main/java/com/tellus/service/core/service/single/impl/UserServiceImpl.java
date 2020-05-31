package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.UserEntity;
import com.tellus.service.core.mapper.UserMapper;
import com.tellus.service.core.service.single.UserService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

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

}
