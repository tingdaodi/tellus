package com.tellus.config.operationlog;

import com.tellus.support.OperationLog;
import com.tellus.support.exception.SystemErrorException;

/**
 * 操作日志 Service
 *
 * @author Roy
 * @date 2020/5/21 11:11
 * @see OperationLog
 */
public interface OperationLogService {

    /**
     * 保存日志, 返回ID
     *
     * @param operationLog 日志信息
     * @param <T>          泛型 extends OperationLog
     * @return 日志ID
     */
    default <T extends OperationLog> Integer save(T operationLog) {
        throw new SystemErrorException("Interface not implemented, '[com.tellus.config.operationlog.OperationLogService].save(T operationLog)'");
    }

    /**
     * 更新日志
     *
     * @param operationLog 日志信息
     * @param <T>          泛型 extends OperationLog
     */
    default <T extends OperationLog> void updateById(T operationLog) {
        throw new SystemErrorException("Interface not implemented, '[com.tellus.config.operationlog.OperationLogService].updateId(T operationLog)'");
    }

}
