package com.tellus.service.core.mapper;

import com.tellus.service.core.BaseRelationMapper;
import com.tellus.service.core.model.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 资源信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface ResourceMapper extends BaseRelationMapper<ResourceEntity> {

    /**
     * 根据角色Id, 查询资源
     *
     * @param userId 用户Id
     * @return List
     */
    List<ResourceEntity> selectByUserId(@Param("userId") int userId);

    /**
     * 根据角色Id, 查询资源
     *
     * @param roleIds 角色 Ids
     * @return List
     */
    List<ResourceEntity> selectByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
