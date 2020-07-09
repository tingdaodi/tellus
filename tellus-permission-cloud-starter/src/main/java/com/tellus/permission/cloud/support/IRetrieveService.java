package com.tellus.permission.cloud.support;

import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 读取数据服务接口
 * <p>
 * V - Result VO
 * R - Retrieve VO
 *
 * @author Roy
 * @date 2020/7/9 18:58
 */
public interface IRetrieveService<V, R> {

    /**
     * 查询总数
     *
     * @param retrieveVO 查询条件
     * @return 总数
     */
    Integer count(R retrieveVO);

    /**
     * 根据 Id, 查询一条记录
     *
     * @param id id
     * @return Optional<V>
     */
    Optional<V> findById(Serializable id);

    /**
     * 根据条件查询一条记录, 存在多条抛出异常
     *
     * @param retrieveVO 查询条件
     * @return Optional<V>
     */
    Optional<V> findOne(R retrieveVO);

    /**
     * 查询所有记录
     *
     * @return List<V>
     */
    List<V> listAll();

    /**
     * 根据条件,查询记录
     *
     * @param retrieveVO 查询条件
     * @return List<V>
     */
    List<V> list(R retrieveVO);

    /**
     * 分页查询
     *
     * @param pageInfo 分页条件
     * @return PageWrapper<V>
     */
    PageWrapper<V> page(PageInfo<R> pageInfo);

    /**
     * 知否支持查询所有记录
     *
     * @return Boolean
     */
    Boolean supportedAll();

}
