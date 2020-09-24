package com.tellus.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果包装类
 *
 * @author Roy
 * @date 2020/5/18 18:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Result", description = "返回结果")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8051327402926915862L;
    private static final int SUCCESS_CODE = 200;
    private static final int INTERNAL_ERROR_CODE = 500;

    @ApiModelProperty(value = "返回编号")
    private Integer code = SUCCESS_CODE;

    @ApiModelProperty(value = "是否成功")
    private Boolean successful = Boolean.TRUE;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回结果")
    private T data;

    // ~ 成功结果
    // ~ ===============================================================================================

    public static <T> Result<T> success(Integer code, String message, T data) {
        return Result.<T>builder()
                .successful(Boolean.TRUE)
                .message(message)
                .code(code)
                .data(data)
                .build();
    }

    public static <T> Result<T> success(String message, T data) {
        return Result.success(SUCCESS_CODE, message, data);
    }

    public static <T> Result<T> success(T data) {
        return Result.success(SUCCESS_CODE, "success", data);
    }

    public static <T> Result<T> success() {
        return Result.success(SUCCESS_CODE, "success", null);
    }

    // ~ 失败结果
    // ~ ===============================================================================================

    public static <T> Result<T> error(Integer code, String message, T data) {
        return Result.<T>builder()
                .successful(Boolean.FALSE)
                .message(message)
                .code(code)
                .data(data)
                .build();
    }

    public static <T> Result<T> error(Integer code, T data) {
        return Result.error(code, null, data);
    }

    public static <T> Result<T> error(String message, T data) {
        return Result.error(INTERNAL_ERROR_CODE, message, data);
    }

    public static <T> Result<T> error(Integer code) {
        return Result.error(code, null, null);
    }

    public static <T> Result<T> error(String message) {
        return Result.error(INTERNAL_ERROR_CODE, message, null);
    }

    public static <T> Result<T> error(T data) {
        return Result.error(INTERNAL_ERROR_CODE, null, data);
    }

    public static <T> Result<T> error() {
        return Result.error(INTERNAL_ERROR_CODE, null, null);
    }
}
