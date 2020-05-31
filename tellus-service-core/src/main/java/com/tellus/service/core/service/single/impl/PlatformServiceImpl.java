package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.PlatformEntity;
import com.tellus.service.core.mapper.PlatformMapper;
import com.tellus.service.core.service.single.PlatformService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台信息表 系统支持多平台接入 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class PlatformServiceImpl extends ICustomizeServiceImpl<PlatformMapper, PlatformEntity> implements PlatformService {

}
