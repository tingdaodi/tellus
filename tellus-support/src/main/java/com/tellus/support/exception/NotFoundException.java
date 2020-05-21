package com.tellus.support.exception;

import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 未找到资源
 *
 * @author Roy
 * @date 2020/5/17 18:56
 */
public class NotFoundException extends BaseException {
    private static final long serialVersionUID = -3698203774535742412L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(IErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public NotFoundException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotFoundException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public NotFoundException(Integer code, String description) {
        super(code, description);
    }

    public NotFoundException(Integer code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public NotFoundException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public NotFoundException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
