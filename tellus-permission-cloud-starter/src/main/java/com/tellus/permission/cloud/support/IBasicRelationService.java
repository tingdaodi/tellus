package com.tellus.permission.cloud.support;

import com.tellus.support.model.vo.update.MoveRelationVO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 层级关系通用服务接口
 *
 * @author Roy
 * @date 2020/7/9 19:03
 */
public interface IBasicRelationService<V, S, R, U> extends IBasicService<V, S, R, U> {

    /**
     * 根节点
     */
    Integer ROOT = 0;
    /**
     * 直属层级
     */
    Integer DIRECT_DISTANCE = 1;

    /**
     * 是否存在
     *
     * @param id Id
     * @return Boolean
     */
    default Boolean contains(Serializable id) {
        return findById(id).isPresent();
    }

    /**
     * #{descendant} 为 #{ancestor} 的下级
     *
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return Boolean
     */
    default Boolean isSubordinate(Integer ancestor, Integer descendant) {
        return findPathToAncestor(ancestor, descendant).stream().findAny().isPresent();
    }

    /**
     * #{descendants} 为 #{ancestor} 的下级
     *
     * @param ancestor    上级 Id
     * @param descendants 下级 Id
     * @return Boolean
     */
    default Boolean isSubordinate(Integer ancestor, Set<Integer> descendants) {
        return findSubs(ancestor).containsAll(descendants);
    }

    /**
     * 移动关系
     *
     * @param relationVO 移动条件
     * @return Boolean
     */
    Boolean move(MoveRelationVO relationVO);

    /**
     * 查询 #{ancestors} 的所有子级
     *
     * @param ancestor 上级 Id
     * @return List<V>
     */
    List<V> findSubs(Integer ancestor);

    /**
     * 查询 #{ancestor} 的所有直属下级
     *
     * @param ancestor 上级 Id
     * @return List<V>
     */
    default List<V> findDirectSubs(Integer ancestor) {
        return findSubByDistance(ancestor, DIRECT_DISTANCE);
    }

    /**
     * 查询 (ROOT) 第 #{distance} 层的所有节点
     *
     * @param distance 层级
     * @return List<V>
     */
    default List<V> findRootByDistance(Integer distance) {
        return findSubByDistance(ROOT, distance);
    }

    /**
     * 查询 #{ancestor} 第 #{distance} 层的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<V>
     */
    List<V> findSubByDistance(Integer ancestor, Integer distance);

    /**
     * 查询 #{ancestor} 上级到第 #{distance} 层之间的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<V>
     */
    List<V> findPathSubs(Integer ancestor, Integer distance);

    /**
     * 查询 #{ancestor} (不含) 与 #{descendant} 之间所有的节点
     *
     * @param ancestor   上级 Id
     * @param descendant 子级 Id
     * @return List<V>
     */
    List<V> findPathToAncestor(Integer ancestor, Integer descendant);

    /**
     * 查询 #{descendant} 的直属上层节点
     *
     * @param descendant 下级节点 Id
     * @return T
     */
    default V findDirectParent(Integer descendant) {
        return findParent(descendant, DIRECT_DISTANCE);
    }

    /**
     * 查询 #{descendant} 往上第 #{distance} 层的父节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return T
     */
    V findParent(Integer descendant, Integer distance);

    /**
     * 查询 #{descendant} 往上到第 #{distance} 层父节点之间的所有节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return List<V>
     */
    List<V> findPathParents(Integer descendant, Integer distance);

    /**
     * 查询 (ROOT) 最深层级的所有节点
     *
     * @return List<V>
     */
    default List<V> findRootLowestDistances() {
        return findLowestDistances(ROOT);
    }

    /**
     * 查询指定上级的最深层级的所有节点
     *
     * @param ancestor 上级 Id
     * @return List<V>
     */
    List<V> findLowestDistances(Integer ancestor);

}
