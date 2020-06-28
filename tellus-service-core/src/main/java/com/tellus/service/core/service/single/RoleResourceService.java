package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.RoleResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色资源表 角色与资源关系 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface RoleResourceService extends ICustomizeService<RoleResourceEntity> {

    /**
     * 根据角色 Id, 删除角色-资源关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据资源 Id, 删除角色-资源关系
     *
     * @param resourceIds 资源 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchResourceIds(@Param("resourceIds") List<Integer> resourceIds);

}
