package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.GroupEntity;
import com.tellus.service.core.mapper.GroupMapper;
import com.tellus.service.core.service.single.GroupService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织表 组织信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class GroupServiceImpl extends ICustomizeServiceImpl<GroupMapper, GroupEntity> implements GroupService {

}