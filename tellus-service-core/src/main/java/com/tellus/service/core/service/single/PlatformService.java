package com.tellus.service.core.service.single;

import com.tellus.service.core.model.PlatformEntity;
import com.tellus.crud.service.ICustomizeService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 平台信息表 系统支持多平台接入 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface PlatformService extends ICustomizeService<PlatformEntity> {

    /**
     * 根据用户 Id, 查询产品信息
     *
     * @param userIds 用户 Ids
     * @return List<PlatformEntity>
     */
    List<PlatformEntity> findByUserIds(List<Integer> userIds);

}
