package com.tellus.support.exception;

import com.tellus.support.enums.SystemErrorCodeEnum;
import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * TOKEN 过期
 *
 * @author Roy
 * @date 2020/5/25 23:45
 */
public class TokenExpireException extends BaseException {
    private static final long serialVersionUID = -1850495441250785068L;

    public TokenExpireException() {
        super(SystemErrorCodeEnum.UNAUTHORIZED);
    }

    public TokenExpireException(String message) {
        super(SystemErrorCodeEnum.UNAUTHORIZED, message);
    }

    public TokenExpireException(Throwable cause) {
        super(SystemErrorCodeEnum.UNAUTHORIZED, cause);
    }

    public TokenExpireException(String message, Throwable cause) {
        super(SystemErrorCodeEnum.UNAUTHORIZED, message, cause);
    }

    public TokenExpireException(IErrorCode errorCode) {
        super(errorCode);
    }

    public TokenExpireException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public TokenExpireException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public TokenExpireException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public TokenExpireException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public TokenExpireException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
