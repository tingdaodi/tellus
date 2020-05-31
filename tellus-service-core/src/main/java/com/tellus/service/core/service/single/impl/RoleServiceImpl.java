package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.RoleEntity;
import com.tellus.service.core.mapper.RoleMapper;
import com.tellus.service.core.service.single.RoleService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 角色信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RoleServiceImpl extends ICustomizeServiceImpl<RoleMapper, RoleEntity> implements RoleService {

}
