package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.UserRoleEntity;

import java.util.List;

/**
 * <p>
 * 用户角色表 用户与角色信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface UserRoleService extends ICustomizeService<UserRoleEntity> {

    /**
     * 根据用户 Id, 删除用户-角色关系
     *
     * @param userIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchUserIds(List<Integer> userIds);

    /**
     * 根据角色 Id, 删除用户-角色关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchRoleIds(List<Integer> roleIds);

}
