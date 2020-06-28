package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.GroupRoleMapper;
import com.tellus.service.core.model.GroupRoleEntity;
import com.tellus.service.core.service.single.GroupRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织角色表 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-06
 */
@Service
public class GroupRoleServiceImpl extends ICustomizeServiceImpl<GroupRoleMapper, GroupRoleEntity> implements GroupRoleService {

    @Override
    public boolean removeBatchGroupIds(List<Integer> groupIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchGroupIds(groupIds));
    }

    @Override
    public boolean removeBatchRoleIds(List<Integer> roleIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchRoleIds(roleIds));
    }
}
