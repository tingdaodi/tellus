package com.tellus.support.exception;

import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 通用系统异常类
 *
 * @author Roy
 * @date 2020/5/21 11:13
 */
public class SystemWarnException extends BaseException {
    private static final long serialVersionUID = 662013890588003927L;

    public SystemWarnException() {
        super();
    }

    public SystemWarnException(String message) {
        super(message);
    }

    public SystemWarnException(Throwable cause) {
        super(cause);
    }

    public SystemWarnException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemWarnException(IErrorCode errorCode) {
        super(errorCode);
    }

    public SystemWarnException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public SystemWarnException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SystemWarnException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public SystemWarnException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public SystemWarnException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
