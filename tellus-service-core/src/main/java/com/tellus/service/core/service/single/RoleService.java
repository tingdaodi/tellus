package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeRelationService;
import com.tellus.service.core.model.RoleEntity;

import java.util.List;

/**
 * <p>
 * 角色表 角色信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface RoleService extends ICustomizeRelationService<RoleEntity> {

    /**
     * 根据用户 Id, 查询角色
     *
     * @param userIds 用户 Ids
     * @return List
     */
    List<RoleEntity> findByUserIds(List<Integer> userIds);

    /**
     * 根据组织 Id, 查询角色
     *
     * @param groupIds 组织 Ids
     * @return List
     */
    List<RoleEntity> findByGroupIds(List<Integer> groupIds);

}
