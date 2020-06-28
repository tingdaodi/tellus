package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.FieldMapper;
import com.tellus.service.core.model.FieldEntity;
import com.tellus.service.core.service.single.FieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字段表 字段信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class FieldServiceImpl extends ICustomizeServiceImpl<FieldMapper, FieldEntity> implements FieldService {

    @Override
    public List<FieldEntity> findSubsByRoleIds(List<Integer> roleIds) {
        return baseMapper.selectSubsByRoleIds(roleIds);
    }

    @Override
    public List<FieldEntity> findSubsByRoleIdsOfResource(Integer resourceId, List<Integer> roleIds) {
        return baseMapper.selectSubsByRoleIdsOfResource(resourceId, roleIds);
    }

}
