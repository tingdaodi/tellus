package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.RelationEntity;
import com.tellus.service.core.mapper.RelationMapper;
import com.tellus.service.core.service.single.RelationService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 层级关系表 层级关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class RelationServiceImpl extends ICustomizeServiceImpl<RelationMapper, RelationEntity> implements RelationService {

}
