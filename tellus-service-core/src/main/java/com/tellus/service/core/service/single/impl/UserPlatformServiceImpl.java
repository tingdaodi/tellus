package com.tellus.service.core.service.single.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tellus.crud.service.ICustomizeServiceImpl;
import com.tellus.service.core.mapper.UserPlatformMapper;
import com.tellus.service.core.model.UserPlatformEntity;
import com.tellus.service.core.service.single.UserPlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户平台表 用户与平台关系 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-09
 */
@Service
public class UserPlatformServiceImpl extends ICustomizeServiceImpl<UserPlatformMapper, UserPlatformEntity> implements UserPlatformService {

    @Override
    public boolean removeBatchUserIds(List<Integer> userIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchUserIds(userIds));
    }

    @Override
    public boolean removeBatchPlatformIds(List<Integer> platformIds) {
        return SqlHelper.retBool(baseMapper.deleteBatchPlatformIds(platformIds));
    }
}
