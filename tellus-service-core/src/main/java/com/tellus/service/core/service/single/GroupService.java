package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeRelationService;
import com.tellus.service.core.model.GroupEntity;

import java.util.List;

/**
 * <p>
 * 组织表 组织信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface GroupService extends ICustomizeRelationService<GroupEntity> {

    /**
     * 根据用户 Id, 查询组织
     *
     * @param userIds 用户 Ids
     * @return 组织
     */
    List<GroupEntity> findByUserIds(List<Integer> userIds);

}
