package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface GroupMapper extends BaseMapper<GroupEntity> {

    /**
     * 根据用户 Id, 查询部门
     *
     * @param userIds 用户 Ids
     * @return 部门
     */
    List<GroupEntity> selectByUserIds(@Param("userIds") List<Integer> userIds);

}
