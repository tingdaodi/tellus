package com.tellus.support.interfaces;

/**
 * 层级关系-上级
 *
 * @author Roy
 * @date 2020/5/18 17:39
 */
public interface IAncestor {

    /**
     * 获取上级
     *
     * @return 上级
     */
    Integer getAncestor();

    /**
     * 设置上级
     *
     * @param ancestor 上级
     */
    void setAncestor(Integer ancestor);

}
