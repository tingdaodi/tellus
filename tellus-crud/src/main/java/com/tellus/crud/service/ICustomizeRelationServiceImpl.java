package com.tellus.crud.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 自定义: ICustomizeRelationService 层级关系拓展实现类
 *
 * @author Roy
 * @date 2020/6/26 12:21
 */
public class ICustomizeRelationServiceImpl<M extends BaseRelationMapper<T>, T> extends ICustomizeServiceImpl<M, T> implements ICustomizeRelationService<T> {
    @Override
    public List<T> findSubs(List<Integer> ancestors, boolean includeSelf) {
        return baseMapper.selectSubs(ancestors, includeSelf);
    }

    @Override
    public List<T> findByDistance(int ancestor, int distance) {
        return baseMapper.selectByDistance(ancestor, distance);
    }

    @Override
    public List<T> findPathSubs(int ancestor, int distance) {
        return baseMapper.selectPathSubs(ancestor, distance);
    }

    @Override
    public List<T> findPathToAncestor(int ancestor, int descendant) {
        return baseMapper.selectPathToAncestor(ancestor, descendant);
    }

    @Override
    public T findParent(int descendant, int distance) {
        return baseMapper.selectParent(descendant, distance);
    }

    @Override
    public List<T> findPathParents(int descendant, int distance) {
        return baseMapper.selectPathParents(descendant, distance);
    }

    @Override
    public List<T> findLowestDistances(Integer ancestor) {
        return baseMapper.selectLowestDistances(ancestor);
    }

    @Override
    public IPage<T> findWithAncestorPage(IPage<T> page, Wrapper<T> wrapper) {
        return baseMapper.selectWithAncestorPage(page, wrapper);
    }
}
