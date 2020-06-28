package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.RoleMenuMapper;
import com.tellus.service.core.model.RoleMenuEntity;
import com.tellus.service.core.service.single.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 角色与菜单关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RoleMenuServiceImpl extends ICustomizeServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

    @Override
    public boolean removeBatchRoleIds(List<Integer> roleIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchRoleIds(roleIds));
    }

    @Override
    public boolean removeBatchMenuIds(List<Integer> menuIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchMenuIds(menuIds));
    }
}
