package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.GroupUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织用户表 组织与用户关系信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface GroupUserService extends ICustomizeService<GroupUserEntity> {

    /**
     * 根据组织 Id, 删除组织-用户关系
     *
     * @param groupIds 组织 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * 根据用户 Id, 删除组织-用户关系
     *
     * @param userIds 用户 Ids
     * @return int 受影响的行数
     */
    boolean removeBatchUserIds(@Param("userIds") List<Integer> userIds);

}
