package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.RelationMapper;
import com.tellus.service.core.model.RelationEntity;
import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 层级关系表 层级关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RelationServiceImpl extends ICustomizeServiceImpl<RelationMapper, RelationEntity> implements RelationService {

    @Override
    public Integer findCountDistance(RelationTypeEnum type, int ancestor, int distance) {
        return baseMapper.selectCountDistance(type.getCode(), ancestor, distance);
    }

    @Override
    public List<Integer> findSubs(RelationTypeEnum type, int ancestor) {
        return baseMapper.selectSubs(type.getCode(), ancestor);
    }

    @Override
    public List<Integer> findSubsByDistance(RelationTypeEnum type, int ancestor, int distance) {
        return baseMapper.selectSubsByDistance(type.getCode(), ancestor, distance);
    }

    @Override
    public List<Integer> findPathSubsByDistance(RelationTypeEnum type, int ancestor, int distance) {
        return baseMapper.selectPathSubsByDistance(type.getCode(), ancestor, distance);
    }

    @Override
    public List<Integer> findPathToAncestor(RelationTypeEnum type, int ancestor, int descendant) {
        return baseMapper.selectPathToAncestor(type.getCode(), ancestor, descendant);
    }

    @Override
    public Integer findParentByDistance(RelationTypeEnum type, int descendant, int distance) {
        return baseMapper.selectParent(type.getCode(), descendant, distance);
    }

    @Override
    public List<Integer> findPathParents(RelationTypeEnum type, int descendant, int distance) {
        return baseMapper.selectPathParents(type.getCode(), descendant, distance);
    }

    @Override
    public Integer findDistance(RelationTypeEnum type, int ancestor, int descendant) {
        return baseMapper.selectDistance(type.getCode(), ancestor, descendant);
    }

    @Override
    public void savePath(RelationTypeEnum type, int ancestor, int descendant) {
        baseMapper.insertPath(type.getCode(), ancestor, descendant);
    }

    @Override
    public void saveNode(RelationTypeEnum type, int nodeId) {
        baseMapper.insertNode(type.getCode(), nodeId);
    }

    @Override
    public void removePath(RelationTypeEnum type, int nodeId) {
        baseMapper.deletePath(type.getCode(), nodeId);
    }

    @Override
    public Integer findLowestDistances(RelationTypeEnum type, int ancestor) {
        return baseMapper.selectLowestDistances(type.getCode(), ancestor);
    }
}
