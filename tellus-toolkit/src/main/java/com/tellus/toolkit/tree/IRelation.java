package com.tellus.toolkit.tree;

import com.tellus.support.interfaces.ISubordinate;

/**
 * 父子关系
 *
 * @author Roy
 * @date 2020/5/18 21:39
 */
public interface IRelation<K, V extends ISubordinate> {

    /**
     * 获取父ID
     *
     * @param t 关系对象
     * @return 父ID
     */
    K getParentId(V t);

    /**
     * 获取子ID
     *
     * @param t 关系对象
     * @return 子ID
     */
    K getId(V t);

}
