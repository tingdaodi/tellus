package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeRelationServiceImpl;
import com.tellus.service.core.mapper.ResourceMapper;
import com.tellus.service.core.model.ResourceEntity;
import com.tellus.service.core.service.single.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 资源信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class ResourceServiceImpl extends ICustomizeRelationServiceImpl<ResourceMapper, ResourceEntity> implements ResourceService {

    @Override
    public List<ResourceEntity> findByUserId(int userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<ResourceEntity> findByRoleIds(List<Integer> roleIds) {
        return baseMapper.selectByRoleIds(roleIds);
    }
}
