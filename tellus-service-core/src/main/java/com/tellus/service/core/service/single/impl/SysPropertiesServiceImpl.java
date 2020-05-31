package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.SysPropertiesEntity;
import com.tellus.service.core.mapper.SysPropertiesMapper;
import com.tellus.service.core.service.single.SysPropertiesService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 系统运行依赖配置 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class SysPropertiesServiceImpl extends ICustomizeServiceImpl<SysPropertiesMapper, SysPropertiesEntity> implements SysPropertiesService {

}
