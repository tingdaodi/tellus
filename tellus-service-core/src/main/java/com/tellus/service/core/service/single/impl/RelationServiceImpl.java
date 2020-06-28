package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.RelationMapper;
import com.tellus.service.core.model.RelationEntity;
import com.tellus.service.core.service.single.RelationService;
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
    public Integer findCountDistance(int type, int ancestor, int distance) {
        return baseMapper.selectCountDistance(type, ancestor, distance);
    }

    @Override
    public List<Integer> findSubs(int type, int ancestor) {
        return baseMapper.selectSubs(type, ancestor);
    }

    @Override
    public List<Integer> findSubsByDistance(int type, int ancestor, int distance) {
        return baseMapper.selectSubsByDistance(type, ancestor, distance);
    }

    @Override
    public List<Integer> findPathSubsByDistance(int type, int ancestor, int distance) {
        return baseMapper.selectPathSubsByDistance(type, ancestor, distance);
    }

    @Override
    public List<Integer> findPathToAncestor(int type, int ancestor, int descendant) {
        return baseMapper.selectPathToAncestor(type, ancestor, descendant);
    }

    @Override
    public List<Integer> findPathParent(int type, int descendant, int distance) {
        return baseMapper.selectPathParent(type, descendant, distance);
    }

    @Override
    public List<Integer> findPathParents(int type, int descendant, int distance) {
        return baseMapper.selectPathParents(type, descendant, distance);
    }

    @Override
    public Integer findDistance(int type, int ancestor, int descendant) {
        return baseMapper.selectDistance(type, ancestor, descendant);
    }

    @Override
    public void savePath(int type, int ancestor, int descendant) {
        baseMapper.insertPath(type, ancestor, descendant);
    }

    @Override
    public void saveNode(int type, int nodeId) {
        baseMapper.insertNode(type, nodeId);
    }

    @Override
    public void removePath(int type, int nodeId) {
        baseMapper.deletePath(type, nodeId);
    }

    @Override
    public Integer findLowestDistances(int type, int ancestor) {
        return baseMapper.selectLowestDistances(type, ancestor);
    }
}
