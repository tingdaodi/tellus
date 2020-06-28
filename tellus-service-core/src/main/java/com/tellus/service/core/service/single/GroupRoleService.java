package com.tellus.service.core.service.single;

import com.tellus.service.core.model.GroupRoleEntity;
import com.tellus.crud.service.ICustomizeService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织角色表 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-06
 */
public interface GroupRoleService extends ICustomizeService<GroupRoleEntity> {

    /**
     * 根据组织 Id, 删除组织-角色关系
     *
     * @param groupIds 组织 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * 根据角色 Id, 删除组织-角色关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchRoleIds(@Param("roleIds") List<Integer> roleIds);
}
