package com.tellus.permission.cloud.service;

import com.tellus.config.operationlog.OperationLogService;
import com.tellus.permission.cloud.support.IRetrieveService;
import com.tellus.support.OperationLog;
import com.tellus.support.model.vo.retrieve.RetrieveOperationLogVO;

/**
 * 操作日志服务类
 *
 * @author Roy
 * @date 2020/7/10 17:05
 */
public interface IOperationLogService extends OperationLogService, IRetrieveService<OperationLog, RetrieveOperationLogVO> {
}
