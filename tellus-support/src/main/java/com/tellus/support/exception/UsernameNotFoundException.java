package com.tellus.support.exception;

import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 用户名不存在
 *
 * @author Roy
 * @date 2020/6/25 11:23
 */
public class UsernameNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 59992117398863462L;

    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(IErrorCode errorCode) {
        super(errorCode);
    }

    public UsernameNotFoundException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UsernameNotFoundException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public UsernameNotFoundException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public UsernameNotFoundException(Integer code, String description) {
        super(code, description);
    }

    public UsernameNotFoundException(Integer code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public UsernameNotFoundException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public UsernameNotFoundException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
