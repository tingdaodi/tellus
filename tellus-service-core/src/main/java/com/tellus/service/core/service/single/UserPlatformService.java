package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.UserPlatformEntity;

import java.util.List;

/**
 * <p>
 * 用户平台表 用户与平台关系 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-09
 */
public interface UserPlatformService extends ICustomizeService<UserPlatformEntity> {

    /**
     * 根据用户 Id, 删除用户-平台关系
     *
     * @param userIds 角色 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchUserIds(List<Integer> userIds);

    /**
     * 根据平台 Id, 删除用户-平台关系
     *
     * @param platformIds 平台 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchPlatformIds(List<Integer> platformIds);

}
