package com.tellus.config.dozer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;

/**
 * 分页对象转接口
 *
 * @author Roy
 * @date 2020/5/21 12:37
 */
public interface IPageHelperGenerator {

    /**
     * (分页) IPage -> PageWrapper
     *
     * @param src 分页对象
     * @param clz 转换类型
     * @param <T> Entity
     * @param <V> V
     * @param <S> IPage
     * @return PageWrapper<V>
     */
    <T, V, S extends IPage<T>> PageWrapper<V> convert(S src, Class<V> clz);

    /**
     * (分页) PageWrapper<T> -> PageWrapper<V>
     *
     * @param wrapper 分页对象
     * @param clz     转换类型
     * @param <T>     Entity
     * @param <V>     V
     * @param <S>     PageWrapper
     * @return PageWrapper<V>
     */
    <T, V, S extends PageWrapper<T>> PageWrapper<V> convert(S wrapper, Class<V> clz);

    /**
     * (分页) PageInfo<T> -> PageInfo<V>
     *
     * @param pageInfo 分页对象
     * @param clz      转换类型
     * @param <T>      Entity
     * @param <V>      V
     * @param <S>      PageInfo
     * @return PageInfo<V>
     */
    <T, V, S extends PageInfo<V>> PageInfo<V> convert(S pageInfo, Class<V> clz);
}
