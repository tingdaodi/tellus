package com.tellus.service.core.mapper;

import com.tellus.service.core.BaseRelationMapper;
import com.tellus.service.core.model.GroupEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织表 组织信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface GroupMapper extends BaseRelationMapper<GroupEntity> {

    /**
     * 根据用户 Id, 查询组织
     *
     * @param userIds 用户 Ids
     * @return 组织
     */
    List<GroupEntity> selectByUserIds(@Param("userIds") List<Integer> userIds);

}
