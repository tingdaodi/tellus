package com.tellus.support.enums;

import com.tellus.support.interfaces.IErrorCode;
import lombok.Getter;

/**
 * 系统错误码
 *
 * @author Roy
 * @date 2020/5/15 22:59
 */
@Getter
public enum SystemErrorCodeEnum implements IErrorCode {

    /**
     * 数据格式错误
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * TOKEN 过期，或者未认证
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 无权限访问资源
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * 未找到资源
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 不能访问的方法
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * 服务器正忙
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * 值唯一
     */
    UNIQUENESS(1000, "%s已存在"),
    ;

    private final Integer code;
    private final String description;

    SystemErrorCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
