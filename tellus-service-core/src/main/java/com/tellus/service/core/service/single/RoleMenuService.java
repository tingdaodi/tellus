package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.RoleMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 角色与菜单关系 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface RoleMenuService extends ICustomizeService<RoleMenuEntity> {

    /**
     * 根据角色 Id, 删除角色-菜单关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据菜单 Id, 删除角色-菜单关系
     *
     * @param menuIds 菜单 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchMenuIds(@Param("menuIds") List<Integer> menuIds);

}
