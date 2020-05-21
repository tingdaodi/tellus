package com.tellus.support.interfaces;

/**
 * 层级关系-下级
 *
 * @author Roy
 * @date 2020/5/18 17:41
 */
public interface IDescendant {

    /**
     * 获取下级
     *
     * @return 下级
     */
    Integer getId();

    /**
     * 设置下级
     *
     * @param descendant 下级
     */
    void setId(Integer descendant);

}
