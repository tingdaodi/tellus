package com.tellus.service.core.mapper;

import com.tellus.service.core.BaseRelationMapper;
import com.tellus.service.core.model.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 角色信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface RoleMapper extends BaseRelationMapper<RoleEntity> {

    /**
     * 根据用户 Id, 查询角色
     *
     * @param userIds 用户 Ids
     * @return List
     */
    List<RoleEntity> selectByUserIds(@Param("userIds") List<Integer> userIds);

    /**
     * 根据组织 Id, 查询角色
     *
     * @param groupIds 组织 Ids
     * @return List
     */
    List<RoleEntity> selectByGroupIds(@Param("groupIds") List<Integer> groupIds);

}
