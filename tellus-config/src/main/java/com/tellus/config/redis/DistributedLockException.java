package com.tellus.config.redis;

import com.tellus.support.exception.BaseException;
import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 分布式锁失败
 *
 * @author Roy
 * @date 2020/5/22 13:48
 */
public class DistributedLockException extends BaseException {
    private static final long serialVersionUID = -89415963342811148L;

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(IErrorCode errorCode) {
        super(errorCode);
    }

    public DistributedLockException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DistributedLockException(Integer code, String description) {
        super(code, description);
    }

    public DistributedLockException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public DistributedLockException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
