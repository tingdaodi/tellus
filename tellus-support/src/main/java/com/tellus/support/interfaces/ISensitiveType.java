package com.tellus.support.interfaces;

/**
 * 敏感数据加密类型接口
 *
 * @author Roy
 * @date 2020/5/24 0:56
 */
public interface ISensitiveType {

    /**
     * 参数名称
     *
     * @return 参数名称
     */
    String getName();

    /**
     * 加密 KEY 前缀
     *
     * @return 加密 KEY 前缀
     */
    String getKeyPrefix();

}
