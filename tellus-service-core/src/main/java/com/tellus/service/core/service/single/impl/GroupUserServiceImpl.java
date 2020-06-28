package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.service.core.model.GroupUserEntity;
import com.tellus.service.core.mapper.GroupUserMapper;
import com.tellus.service.core.service.single.GroupUserService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织用户表 组织与用户关系信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class GroupUserServiceImpl extends ICustomizeServiceImpl<GroupUserMapper, GroupUserEntity> implements GroupUserService {

    @Override
    public boolean removeBatchGroupIds(List<Integer> groupIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchGroupIds(groupIds));
    }

    @Override
    public boolean removeBatchUserIds(List<Integer> userIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchUserIds(userIds));
    }
}
