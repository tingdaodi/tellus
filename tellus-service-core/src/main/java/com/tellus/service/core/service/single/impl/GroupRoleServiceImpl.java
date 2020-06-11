package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.GroupRoleEntity;
import com.tellus.service.core.mapper.GroupRoleMapper;
import com.tellus.service.core.service.GroupRoleService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

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

}
