package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.PlatformEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 平台信息表 系统支持多平台接入 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface PlatformMapper extends BaseMapper<PlatformEntity> {

    /**
     * 根据用户 Id, 查询产品信息
     *
     * @param userIds 用户 Ids
     * @return List<PlatformEntity>
     */
    List<PlatformEntity> selectByUserIds(@Param("userIds") List<Integer> userIds);

}
