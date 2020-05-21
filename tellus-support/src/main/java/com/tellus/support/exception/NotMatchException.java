package com.tellus.support.exception;

import com.tellus.support.interfaces.IErrorCode;

import java.util.function.Supplier;

/**
 * 数据格式错误, 非法参数
 *
 * @author Roy
 * @date 2020/5/17 18:58
 */
public class NotMatchException extends BaseException {
    private static final long serialVersionUID = 3986295862659418868L;

    public NotMatchException() {
    }

    public NotMatchException(String message) {
        super(message);
    }

    public NotMatchException(Throwable cause) {
        super(cause);
    }

    public NotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMatchException(IErrorCode errorCode) {
        super(errorCode);
    }

    public NotMatchException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public NotMatchException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotMatchException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public NotMatchException(Integer code, String description) {
        super(code, description);
    }

    public NotMatchException(Integer code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public NotMatchException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(errorCode, descriptionSupplier);
    }

    public NotMatchException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(errorCode, cause, descriptionSupplier);
    }
}
