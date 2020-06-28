package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeRelationService;
import com.tellus.service.core.model.ResourceEntity;

import java.util.List;

/**
 * <p>
 * 资源表 资源信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface ResourceService extends ICustomizeRelationService<ResourceEntity> {

    /**
     * 根据角色Id, 查询资源
     *
     * @param userId 用户Id
     * @return List
     */
    List<ResourceEntity> findByUserId(int userId);

    /**
     * 根据角色Id, 查询资源
     *
     * @param roleIds 角色 Ids
     * @return List
     */
    List<ResourceEntity> findByRoleIds(List<Integer> roleIds);

}
