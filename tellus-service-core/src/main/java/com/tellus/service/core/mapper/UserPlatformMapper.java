package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.UserPlatformEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户平台表 用户与平台关系 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-09
 */
public interface UserPlatformMapper extends BaseMapper<UserPlatformEntity> {

    /**
     * 根据用户Id, 批量删除用户-平台关系
     *
     * @param userIds 用户 Ids
     * @return 受影响的行数
     */
    int deleteBatchUserIds(@Param("userIds") List<Integer> userIds);

    /**
     * 根据平台Id, 批量删除用户-平台关系
     *
     * @param platformIds 平台 Ids
     * @return 受影响的行数
     */
    int deleteBatchPlatformIds(@Param("platformIds") List<Integer> platformIds);
}
