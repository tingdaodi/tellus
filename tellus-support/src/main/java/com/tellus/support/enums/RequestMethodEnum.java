package com.tellus.support.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 请求方式: GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE
 *
 * @author Roy
 * @date 2020/6/30 18:04
 */
@Getter
@ApiModel(value = "RequestMethod", description = "请求方式")
public enum RequestMethodEnum {
    /**
     * 请求方式
     */
    GET(1), HEAD(2), POST(3), PUT(4), PATCH(5), DELETE(6), OPTIONS(7), TRACE(8);

    @EnumValue
    private final Integer code;

    RequestMethodEnum(Integer code) {
        this.code = code;
    }
}
