package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.service.core.model.RoleResourceEntity;
import com.tellus.service.core.mapper.RoleResourceMapper;
import com.tellus.service.core.service.single.RoleResourceService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色资源表 角色与资源关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RoleResourceServiceImpl extends ICustomizeServiceImpl<RoleResourceMapper, RoleResourceEntity> implements RoleResourceService {

    @Override
    public boolean removeBatchRoleIds(List<Integer> roleIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchRoleIds(roleIds));
    }

    @Override
    public boolean removeBatchResourceIds(List<Integer> resourceIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchResourceIds(resourceIds));
    }
}
