package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.OperationLogEntity;
import com.tellus.service.core.mapper.OperationLogMapper;
import com.tellus.service.core.service.single.OperationLogService;
import com.tellus.crud.service.ICustomizeServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 记录操作信息 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class OperationLogServiceImpl extends ICustomizeServiceImpl<OperationLogMapper, OperationLogEntity> implements OperationLogService {

}
