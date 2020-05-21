package com.tellus.support.exception;

import com.tellus.support.enums.error.SystemErrorCodeEnum;
import com.tellus.support.interfaces.IErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * 底层基础通用异常类
 *
 * @author Roy
 * @date 2020/5/17 18:23
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8810388364270137257L;
    protected static final Integer CODE = SystemErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode();
    protected static final String DESCRIPTION = SystemErrorCodeEnum.INTERNAL_SERVER_ERROR.getDescription();

    private Integer code;
    private String description;

    // ~ 默认系统内部异常构造函数： SystemErrorCodeEnum.INTERNAL_SERVER_ERROR
    // ~ =========================================================================

    public BaseException() {
        this.code = CODE;
        this.description = DESCRIPTION;
    }

    public BaseException(String message) {
        super(message);
        this.code = CODE;
        this.description = DESCRIPTION;
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.code = CODE;
        this.description = DESCRIPTION;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = CODE;
        this.description = DESCRIPTION;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = CODE;
        this.description = DESCRIPTION;
    }

    // ~ 自定义错误码构造方法： IErrorCode
    // ~ =========================================================================

    public BaseException(IErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(IErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(IErrorCode errorCode, Throwable cause) {
        super(cause);
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(IErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(Integer code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public BaseException(Integer code, String description, Throwable cause) {
        super(cause);
        this.code = code;
        this.description = description;
    }

    public BaseException(Integer code, String description, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(description, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.description = description;
    }

    // ~ Supplier 表达式

    public BaseException(IErrorCode errorCode, Supplier<String> descriptionSupplier) {
        super(descriptionSupplier.get());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BaseException(IErrorCode errorCode, Throwable cause, Supplier<String> descriptionSupplier) {
        super(descriptionSupplier.get(), cause);
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }


    // ~ Override Method
    // ~ =========================================================================

    @Override
    public String getMessage() {
        if (description.equals(super.getMessage())) {
            return description;
        }
        return "(" + description + ")" + super.getMessage();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BaseException.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("description=" + description)
                .add(super.toString())
                .toString();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
