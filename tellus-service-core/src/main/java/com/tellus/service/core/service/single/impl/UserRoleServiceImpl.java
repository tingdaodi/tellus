package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.UserRoleMapper;
import com.tellus.service.core.model.UserRoleEntity;
import com.tellus.service.core.service.single.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 用户与角色信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class UserRoleServiceImpl extends ICustomizeServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

    @Override
    public boolean removeBatchUserIds(List<Integer> userIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchUserIds(userIds));
    }

    @Override
    public boolean removeBatchRoleIds(List<Integer> roleIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchRoleIds(roleIds));
    }
}
