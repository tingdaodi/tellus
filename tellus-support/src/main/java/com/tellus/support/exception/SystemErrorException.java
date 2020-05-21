package com.tellus.support.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.tellus.support.interfaces.IErrorCode;

/**
 * 通用系统异常类-可能包含敏感信息,不传递到客户端
 *
 * @author Roy
 * @date 2020/5/21 11:13
 */
public class SystemErrorException extends BaseException {
    private static final long serialVersionUID = 662013890588003927L;

    public SystemErrorException() {
        super();
    }

    public SystemErrorException(String message) {
        super(message);
    }

    public SystemErrorException(Throwable cause) {
        super(cause);
    }

    public SystemErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemErrorException(IErrorCode errorCode) {
        super(errorCode);
    }

    public SystemErrorException(IErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public SystemErrorException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SystemErrorException(IErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
