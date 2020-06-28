package com.tellus.service.core.service.single.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.RoleFieldMapper;
import com.tellus.service.core.model.RoleFieldEntity;
import com.tellus.service.core.service.single.RoleFieldService;
import com.tellus.support.enums.DisplayModeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Integer removeBatchWithSubs(Integer roleId, List<Integer> fieldIds) {
        return baseMapper.deleteBatchWithSubs(roleId, fieldIds);
    }

    @Override
    public Integer updateBatchWithSubs(List<RoleFieldEntity> entities) {
        return baseMapper.updateBatchWithSubs(entities);
    }

    @Override
    public List<RoleFieldEntity> findSubs(List<Integer> roleIds) {
        return baseMapper.selectSubs(roleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean changeRoleFieldWithSubs(Integer roleId, Map<Integer, DisplayModeEnum> fields) {
        //  字段集合为空, 则清除当前角色及下级角色所有的角色-字段关系数据
        if (fields.isEmpty()) {
            this.removeBatchWithSubs(roleId, Collections.emptyList());
            return true;
        }

        //  入参合集
        List<RoleFieldEntity> incomings = fields.entrySet()
                .stream()
                .map(entity -> RoleFieldEntity.builder().roleId(roleId).fieldId(entity.getKey())
                        .displayMode(entity.getValue()).build())
                .collect(Collectors.toList());

        //  入参字段 Id 集合
        Set<Integer> incomingFieldIds = incomings.stream().map(RoleFieldEntity::getFieldId).collect(Collectors.toSet());
        //  角色及所有下级的字段信息
        List<RoleFieldEntity> subs = this.findSubs(Lists.newArrayList(roleId));
        //  角色已有字段 Id 集合
        Set<Integer> subIds = subs.stream().map(RoleFieldEntity::getFieldId).collect(Collectors.toSet());

        //  找出不在入参中的数据, 并删除
        List<Integer> reducesOfRemove = Lists.newArrayList(Sets.difference(subIds, incomingFieldIds));
        if (!reducesOfRemove.isEmpty()) {
            this.removeBatchWithSubs(roleId, reducesOfRemove);
        }

        //  找出入参中新增的数据, 并插入
        List<RoleFieldEntity> reducesOfInsert = incomings.stream().filter(t -> !subIds.contains(t.getFieldId()))
                .collect(Collectors.toList());
        if (!reducesOfInsert.isEmpty()) {
            this.saveBatch(reducesOfInsert);
        }

        //  找出交集数据, 并更新
        Map<Integer, DisplayModeEnum> temp = subs
                .stream()
                .collect(Collectors.toMap(
                        RoleFieldEntity::getFieldId,
                        RoleFieldEntity::getDisplayMode,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        //  显示方式已变更的, 则更新
        List<RoleFieldEntity> intersections = incomings
                .stream()
                .filter(t -> temp.containsKey(t.getFieldId()) && !temp.containsValue(t.getDisplayMode()))
                .collect(Collectors.toList());

        if (!intersections.isEmpty()) {
            this.updateBatchWithSubs(intersections);
        }

        return true;
    }
}
