package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.RoleFieldEntity;
import com.tellus.service.core.mapper.RoleFieldMapper;
import com.tellus.service.core.service.single.RoleFieldService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色字段表 角色与字段关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RoleFieldServiceImpl extends ICustomizeServiceImpl<RoleFieldMapper, RoleFieldEntity> implements RoleFieldService {

}
