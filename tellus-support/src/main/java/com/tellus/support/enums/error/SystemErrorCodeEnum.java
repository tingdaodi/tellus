package com.tellus.support.enums.error;

import com.tellus.support.interfaces.BaseErrorInfo;
import lombok.Getter;

/**
 * 系统错误码
 *
 * @author Roy
 * @date 2020/5/15 22:59
 */
@Getter
public enum SystemErrorCodeEnum implements BaseErrorInfo {

    /**
     * 数据格式错误
     */
    BAD_REQUEST(400, "Bad Request"),

    ;

    private final Integer code;
    private final String description;

    SystemErrorCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
