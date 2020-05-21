package com.tellus.support.exception;

import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 无权限访问资源
 *
 * @author Roy
 * @date 2020/5/17 18:59
 */
public class AccessDeniedException extends BaseException {
    private static final long serialVersionUID = 9190787126877400471L;

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(IErrorCode errorCode) {
        super(errorCode);
    }

    public AccessDeniedException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AccessDeniedException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AccessDeniedException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public AccessDeniedException(Integer code, String description) {
        super(code, description);
    }

    public AccessDeniedException(Integer code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public AccessDeniedException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public AccessDeniedException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
