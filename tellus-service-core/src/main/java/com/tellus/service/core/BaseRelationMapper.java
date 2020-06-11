package com.tellus.service.core;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 继承该接口后, 无需编写 mapper.xml 文件, 即可获得CRUD功能, 以及层级 RELATION 操作功能
 *
 * @author Roy
 * @date 2020/6/2 14:07
 */
public interface BaseRelationMapper<T> extends BaseMapper<T> {

    /**
     * 查询 #{ancestors} 的第 #{distance} 层的所有子级
     * <p>
     * {@code distance == null} 查询所有子级
     * {@code includeSelf == null || includeSelf == false} 不包含 #{ancestors}
     *
     * @param ancestors   上级 Id
     * @param distance    层级
     * @param includeSelf 包含自身 #{ancestor}
     * @return List<T>
     */
    List<T> selectSubs(@Param("ancestors") List<Integer> ancestors,
                       @Param("distance") int distance,
                       @Param("includeSelf") boolean includeSelf);

    /**
     * 查询 #{ancestor} 第 #{distance} 层的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<T>
     */
    List<T> selectByDistance(@Param("ancestor") int ancestor,
                             @Param("distance") int distance);

    /**
     * 查询 #{ancestor} 上级到第 #{distance} 层之间的所有节点
     *
     * @param ancestor 上级 Id
     * @param distance 层级
     * @return List<T>
     */
    List<T> selectPathSubs(@Param("ancestor") int ancestor, @Param("distance") int distance);

    /**
     * 查询 #{ancestor} (不含) 与 #{descendant} 之间所有的节点
     *
     * @param ancestor   上级 Id
     * @param descendant 子级 Id
     * @return List<T>
     */
    List<T> selectPathToAncestor(@Param("ancestor") int ancestor, @Param("descendant") int descendant);

    /**
     * 查询 #{descendant} 往上第 #{distance} 层的父节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return T
     */
    T selectParent(@Param("descendant") int descendant, @Param("distance") int distance);

    /**
     * 查询 #{descendant} 往上到第 #{distance} 层父节点之间的所有节点
     *
     * @param descendant 子级 Id
     * @param distance   层级
     * @return List<T>
     */
    List<T> selectPathParents(@Param("descendant") int descendant, @Param("distance") int distance);

    /**
     * 查询指定上级的最深层级的所有节点
     *
     * @param ancestor 上级 Id
     * @return List<T>
     */
    List<T> selectLowestDistances(@Param("ancestor") Integer ancestor);

    /**
     * 根据条件分页查询, 返回直属上级 Id
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @return IPage<T>
     */
    IPage<T> selectWithAncestorPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> wrapper);

}
