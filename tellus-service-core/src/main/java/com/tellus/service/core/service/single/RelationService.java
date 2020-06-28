package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.RelationEntity;

import java.util.List;

/**
 * <p>
 * 层级关系表 层级关系 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface RelationService extends ICustomizeService<RelationEntity> {

    /**
     * 查询 #{ancestor} 下第 #{distance} 层节点总数
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return Integer
     */
    Integer findCountDistance(int type, int ancestor, int distance);

    /**
     * 查询 #{ancestor} 的所有子节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return List<Integer>
     */
    List<Integer> findSubs(int type, int ancestor);

    /**
     * 查询上级节点 #{ancestor} 的第 #{distance} 层的所有节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    List<Integer> findSubsByDistance(int type, int ancestor, int distance);

    /**
     * 查询上级节点 #{ancestor} 的往下到第 #{distance} 层之间所有的节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    List<Integer> findPathSubsByDistance(int type, int ancestor, int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间所有的节点
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return List<Integer>
     */
    List<Integer> findPathToAncestor(int type, int ancestor, int descendant);

    /**
     * 查询 #{descendant} 第 #{distance} 的父节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    List<Integer> findPathParent(int type, int descendant, int distance);

    /**
     * 查询 #{descendant} 节点 到第 #{distance} 层父节点之间的所有节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    List<Integer> findPathParents(int type, int descendant, int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间的距离
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return Integer
     */
    Integer findDistance(int type, int ancestor, int descendant);

    /**
     * 将 #{descendant} 插入到 #{ancestor}
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     */
    void savePath(int type, int ancestor, int descendant);

    /**
     * 插入节点自身
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    void saveNode(int type, int nodeId);

    /**
     * 从树中删除节点的路径
     * 指定的子节点可能存在子树, 而子树的节点在该节点至上的路径并没有改变,
     * 因此使用该方法后还必须手动修改子树的路径以确保树的连续性
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    void removePath(int type, int nodeId);

    /**
     * 查询 #{ancestor} 子树的最大深度
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return Integer
     */
    Integer findLowestDistances(int type, int ancestor);

}
