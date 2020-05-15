package com.tellus.support.interfaces;

/**
 * 错误信息接口
 *
 * @author Roy
 * @date 2020/5/15 22:52
 */
public interface BaseErrorInfo {

    /**
     * 系统自定义异常标识
     *
     * @return 系统自定义异常标识
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getDescription();

}
