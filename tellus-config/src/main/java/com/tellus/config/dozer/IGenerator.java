package com.tellus.config.dozer;

import java.util.List;
import java.util.Set;

/**
 * 深度复制工具接口
 *
 * @author Roy
 * @date 2020/5/21 12:11
 */
public interface IGenerator {

    /**
     * 单个对象的深度复制
     *
     * @param src 元数据
     * @param clz 类型
     * @param <T> 泛型
     * @param <S> 泛型
     * @return T
     */
    <T, S> T convert(S src, Class<T> clz);

    /**
     * List 深度复制
     *
     * @param src 元数据
     * @param clz 类型
     * @param <T> 泛型
     * @param <S> 泛型
     * @return T
     */
    <T, S> List<T> convert(List<S> src, Class<T> clz);

    /**
     * Set 深度复制
     *
     * @param src 元数据
     * @param clz 类型
     * @param <T> 泛型
     * @param <S> 泛型
     * @return T
     */
    <T, S> Set<T> convert(Set<S> src, Class<T> clz);

    /**
     * Array 深度复制
     *
     * @param src 元数据
     * @param clz 类型
     * @param <T> 泛型
     * @param <S> 泛型
     * @return T
     */
    <T, S> T[] convert(S[] src, Class<T> clz);

}
