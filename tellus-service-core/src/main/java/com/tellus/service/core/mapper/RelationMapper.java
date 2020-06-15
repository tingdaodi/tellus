package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.RelationEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 层级关系表 层级关系 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface RelationMapper extends BaseMapper<RelationEntity> {

    /**
     * 查询 #{ancestor} 下第 #{distance} 层节点总数
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return Integer
     */
    @Select("select count(1) from t_relation where type=#{type} and ancestor=#{ancestor} and distance=#{distance}")
    Integer selectCountDistance(@Param("type") int type, @Param("ancestor") int ancestor, @Param("distance") int distance);

    /**
     * 查询 #{ancestor} 的所有子节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return List<Integer>
     */
    @Select("select descendant from t_relation where type=#{type} and ancestor=#{ancestor} and distance>0 ")
    List<Integer> selectSubs(@Param("type") int type, @Param("ancestor") int ancestor);

    /**
     * 查询上级节点 #{ancestor} 的第 #{distance} 层的所有节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    @Select("select descendant from t_relation where type=#{type} and ancestor=#{ancestor} and distance=#{distance}")
    List<Integer> selectSubsByDistance(@Param("type") int type, @Param("ancestor") int ancestor, @Param("distance") int distance);

    /**
     * 查询上级节点 #{ancestor} 的往下到第 #{distance} 层之间所有的节点
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<Integer>
     */
    @Select("select descendant from t_relation where type=#{type} and ancestor=#{ancestor} and distance<=#{distance}")
    List<Integer> selectPathSubsByDistance(@Param("type") int type, @Param("ancestor") int ancestor, @Param("distance") int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间所有的节点
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return List<Integer>
     */
    @Select({
            "select ancestor from t_relation where type=#{type} and ancestor=#{ancestor} and distance < ",
            "(select distance from t_relation where descendant=#{descendant} and ancestor=#{ancestor}) ",
            "order by distance desc"
    })
    List<Integer> selectPathToAncestor(@Param("type") int type, @Param("ancestor") int ancestor, @Param("descendant") int descendant);

    /**
     * 查询 #{descendant} 第 #{distance} 的父节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    @Select("select ancestor from t_relation where type=#{type} and descendant=#{descendant} and distance=#{distance}")
    List<Integer> selectPathParent(@Param("type") int type, @Param("descendant") int descendant, @Param("distance") int distance);

    /**
     * 查询 #{descendant} 节点 到第 #{distance} 层父节点之间的所有节点
     *
     * @param type       关系类型
     * @param descendant 下级 Id
     * @param distance   层级
     * @return List<Integer>
     */
    @Select("select ancestor from t_relation where type=#{type} and descendant=#{descendant} and distance<#{distance}")
    List<Integer> selectPathParents(@Param("type") int type, @Param("descendant") int descendant, @Param("distance") int distance);

    /**
     * 查询 #{ancestor} 与 #{descendant} 之间的距离
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     * @return Integer
     */
    @Select("select distance from t_relation where type=#{type} and descendant=#{descendant} and ancestor=#{ancestor}")
    Integer selectDistance(@Param("type") int type, @Param("ancestor") int ancestor, @Param("descendant") int descendant);

    /**
     * 将 #{descendant} 插入到 #{ancestor}
     *
     * @param type       关系类型
     * @param ancestor   上级 Id
     * @param descendant 下级 Id
     */
    @Select({
            "insert into t_relation(type, ancestor, descendant, distance) ",
            "(select type,ancestor,#{descendant},distance+1 from t_relation where type=#{type} and descendant=#{ancestor})"
    })
    void insertPath(@Param("type") int type, @Param("ancestor") int ancestor, @Param("descendant") int descendant);

    /**
     * 插入节点自身
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    @Insert("insert into t_relation(type, ancestor, descendant, distance) values(#{type}, #{nodeId}, #{nodeId}, 0)")
    void insertNode(@Param("type") int type, @Param("nodeId") int nodeId);

    /**
     * 从树中删除节点的路径
     * 指定的子节点可能存在子树, 而子树的节点在该节点至上的路径并没有改变,
     * 因此使用该方法后还必须手动修改子树的路径以确保树的连续性
     *
     * @param type   关系类型
     * @param nodeId 节点Id
     */
    @Delete("delete from t_relation where type=#{type} and descendant=#{nodeid}")
    void deletePath(@Param("type") int type, @Param("nodeId") int nodeId);

    /**
     * 查询 #{ancestor} 子树的最大深度
     *
     * @param type     关系类型
     * @param ancestor 上级 Id
     * @return Integer
     */
    @Select("select max(distance) as distance from t_relation where type=#{type} and ancestor=#{ancestor} ")
    Integer selectLowestDistances(@Param("type") int type, @Param("ancestor") int ancestor);
}
























