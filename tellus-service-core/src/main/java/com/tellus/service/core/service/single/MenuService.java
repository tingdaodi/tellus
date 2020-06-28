package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeRelationService;
import com.tellus.service.core.model.MenuEntity;

import java.util.List;

/**
 * <p>
 * 菜单表 菜单表 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface MenuService extends ICustomizeRelationService<MenuEntity> {

    /**
     * 根据用户Id, 查询菜单
     *
     * @param userId 用户Id
     * @return List<MenuEntity>
     */
    List<MenuEntity> findByUserId(int userId);

    /**
     * 根据角色Id, 查询菜单
     *
     * @param roleIds 角色 Ids
     * @return List<MenuEntity>
     */
    List<MenuEntity> findByRoleIds(List<Integer> roleIds);

}
