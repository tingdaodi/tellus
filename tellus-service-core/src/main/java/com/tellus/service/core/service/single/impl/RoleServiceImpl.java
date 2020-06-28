package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeRelationServiceImpl;
import com.tellus.service.core.mapper.RoleMapper;
import com.tellus.service.core.model.RoleEntity;
import com.tellus.service.core.service.single.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 角色信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RoleServiceImpl extends ICustomizeRelationServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Override
    public List<RoleEntity> findByUserIds(List<Integer> userIds) {
        return baseMapper.selectByUserIds(userIds);
    }

    @Override
    public List<RoleEntity> findByGroupIds(List<Integer> groupIds) {
        return baseMapper.selectByGroupIds(groupIds);
    }
}
