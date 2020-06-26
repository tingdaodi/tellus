package com.tellus.crud.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.google.common.collect.Lists;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 层级关系通用接口
 *
 * @author Roy
 * @date 2020/6/26 11:33
 */
public interface ICustomizeRelationService<T> extends ICustomizeService<T> {

    /**
     * 根节点
     */
    Integer ROOT = 0;
    /**
     * 直属层级
     */
    Integer DIRECT_DISTANCE = 1;

    /**
     * 查询 #{ancestors} 的所有子级
     *
     * @param ancestor 上级 Id
     * @return List<T>
     */
    default List<T> findSubs(Integer ancestor) {
        return findSubs(Lists.newArrayList(ancestor), Boolean.FALSE);
    }

    /**
     * 查询 #{ancestors} 的所有子级
     * <p>
     * {@code includeSelf == null || includeSelf == false} 不包含 #{ancestors}
     *
     * @param ancestors   上级 Id
     * @param includeSelf 包含自身 #{ancestor}
     * @return List<T>
     */
    List<T> findSubs(List<Integer> ancestors, boolean includeSelf);

    /**
     * 查询 #{ancestor} 的所有直属下级
     *
     * @param ancestor 上级 Id
     * @return List<T>
     */
    default List<T> findDirectSubs(Integer ancestor) {
        return findByDistance(ancestor, DIRECT_DISTANCE);
    }

    /**
     * 查询 (ROOT) 第 #{distance} 层的所有节点
     *
     * @param distance 层级
     * @return List<T>
     */
    default List<T> findRootByDistance(Integer distance) {
        return findByDistance(ROOT, distance);
    }

    /**
     * 查询 #{ancestor} 第 #{distance} 层的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<T>
     */
    List<T> findByDistance(int ancestor,
                           int distance);

    /**
     * 查询 #{ancestor} 上级到第 #{distance} 层之间的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<T>
     */
    List<T> findPathSubs(int ancestor, int distance);

    /**
     * 查询 #{ancestor} (不含) 与 #{descendant} 之间所有的节点
     *
     * @param ancestor   上级 Id
     * @param descendant 子级 Id
     * @return List<T>
     */
    List<T> findPathToAncestor(int ancestor, int descendant);

    /**
     * 查询 #{descendant} 的直属上层节点
     *
     * @param descendant 下级节点 Id
     * @return T
     */
    default T findDirectParent(int descendant) {
        return findParent(descendant, DIRECT_DISTANCE);
    }

    /**
     * 查询 #{descendant} 往上第 #{distance} 层的父节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return T
     */
    T findParent(int descendant, int distance);

    /**
     * 查询 #{descendant} 往上到第 #{distance} 层父节点之间的所有节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return List<T>
     */
    List<T> findPathParents(int descendant, int distance);

    /**
     * 查询 (ROOT) 最深层级的所有节点
     *
     * @return List<T>
     */
    default List<T> findRootLowestDistances() {
        return findLowestDistances(ROOT);
    }

    /**
     * 查询指定上级的最深层级的所有节点
     *
     * @param ancestor 上级 Id
     * @return List<T>
     */
    List<T> findLowestDistances(Integer ancestor);

    /**
     * 根据条件分页查询, 返回直属上级 Id
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @return IPage<T>
     */
    IPage<T> findWithAncestorPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> wrapper);

    /**
     * #{descendant} 为 #{ancestor} 的下级
     *
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return Boolean
     */
    default Boolean isSubordinate(int ancestor, int descendant) {
        return findPathToAncestor(ancestor, descendant).stream().findAny().isPresent();
    }

    /**
     * #{descendants} 为 #{ancestor} 的下级
     *
     * @param ancestor    上级 Id
     * @param descendants 下级 Id
     * @return Boolean
     */
    default Boolean isSubordinate(int ancestor, Set<Integer> descendants) {
        return findSubs(ancestor).containsAll(descendants);
    }
}
