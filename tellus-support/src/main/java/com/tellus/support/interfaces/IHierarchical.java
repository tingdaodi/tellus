package com.tellus.support.interfaces;

/**
 * 层级关系
 *
 * @author Roy
 * @date 2020/6/30 23:32
 */
public interface IHierarchical extends IAncestor {

    /**
     * 获取层级
     *
     * @return 层级
     */
    Integer getDistance();

    /**
     * 设置层级
     *
     * @param distance
     */
    void setDistance(Integer distance);

}
