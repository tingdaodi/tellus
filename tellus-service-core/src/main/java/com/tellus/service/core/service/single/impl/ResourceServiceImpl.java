package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.ResourceEntity;
import com.tellus.service.core.mapper.ResourceMapper;
import com.tellus.service.core.service.single.ResourceService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 资源信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class ResourceServiceImpl extends ICustomizeServiceImpl<ResourceMapper, ResourceEntity> implements ResourceService {

}
