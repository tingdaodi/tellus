package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.SysPropertiesEntity;

/**
 * <p>
 * 系统配置表 系统运行依赖配置 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface SysPropertiesService extends ICustomizeService<SysPropertiesEntity> {

    /**
     * 根据 Key 查询配置
     *
     * @param key 常量 key
     * @return SysPropertiesEntity
     */
    SysPropertiesEntity selectByKey(String key);
}
