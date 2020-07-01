package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.RelationEntity;
import com.tellus.support.enums.RelationTypeEnum;

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
     * 根节点
     */
    Integer ROOT = 0;
    /**
     * 直属层级
     */
    Integer DIRECT_DISTANCE = 1;

    /**
     * 查询 #{ancestor} 下第 #{distance} 层节点总数
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return Integer
     */
    Integer findCountDistance(RelationTypeEnum type, int ancestor, int distance);

    /**
     * 查询 #{ancestor} 的直属下级
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return List<Integer>
     */
    default List<Integer> findDirectSubs(RelationTypeEnum type, int ancestor) {
        return findSubsByDistance(type, ancestor, DIRECT_DISTANCE);
    }

    /**
     * 查询 #{ancestor} 的所有子节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return List<Integer>
     */
    List<Integer> findSubs(RelationTypeEnum type, int ancestor);

    /**
     * 查询上级节点 #{ancestor} 的第 #{distance} 层的所有节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    List<Integer> findSubsByDistance(RelationTypeEnum type, int ancestor, int distance);

    /**
     * 查询上级节点 #{ancestor} 的往下到第 #{distance} 层之间所有的节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    List<Integer> findPathSubsByDistance(RelationTypeEnum type, int ancestor, int distance);

    /**
     * 查询 #{descendant} 的直属父节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @return Integer
     */
    default Integer findDirectParent(RelationTypeEnum type, int descendant) {
        return findParentByDistance(type, descendant, DIRECT_DISTANCE);
    }

    /**
     * 查询 #{descendant} 第 #{distance} 的父节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    Integer findParentByDistance(RelationTypeEnum type, int descendant, int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间所有的节点
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return List<Integer>
     */
    List<Integer> findPathToAncestor(RelationTypeEnum type, int ancestor, int descendant);

    /**
     * 查询 #{descendant} 节点 到第 #{distance} 层父节点之间的所有节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    List<Integer> findPathParents(RelationTypeEnum type, int descendant, int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间的距离
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return Integer
     */
    Integer findDistance(RelationTypeEnum type, int ancestor, int descendant);

    /**
     * 将 #{descendant} 插入到 #{ancestor}
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     */
    void savePath(RelationTypeEnum type, int ancestor, int descendant);

    /**
     * 插入节点自身
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    void saveNode(RelationTypeEnum type, int nodeId);

    /**
     * 从树中删除节点的路径
     * 指定的子节点可能存在子树, 而子树的节点在该节点至上的路径并没有改变,
     * 因此使用该方法后还必须手动修改子树的路径以确保树的连续性
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    void removePath(RelationTypeEnum type, int nodeId);

    /**
     * 查询 #{ancestor} 子树的最大深度
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return Integer
     */
    Integer findLowestDistances(RelationTypeEnum type, int ancestor);

}
