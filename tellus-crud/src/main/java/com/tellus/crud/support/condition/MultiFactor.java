package com.tellus.crud.support.condition;

/**
 * 多条件通用条件因子接口
 *
 * @author Roy
 * @date 2020/5/25 23:58
 */
public interface MultiFactor extends Factor {

    /**
     * between 值1 and 值2
     * not between 值1 and 值2
     *
     * @param toValue 值2
     */
    <V> void setToValue(V toValue);

}
