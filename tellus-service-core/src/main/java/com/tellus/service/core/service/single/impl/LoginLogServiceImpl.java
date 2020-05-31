package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.LoginLogEntity;
import com.tellus.service.core.mapper.LoginLogMapper;
import com.tellus.service.core.service.single.LoginLogService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 记录登录日志 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class LoginLogServiceImpl extends ICustomizeServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

}
