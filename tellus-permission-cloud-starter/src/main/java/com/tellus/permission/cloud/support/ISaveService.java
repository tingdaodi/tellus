package com.tellus.permission.cloud.support;

import java.util.Collection;

/**
 * 保存记录 Service 通用接口
 * <p>
 * S - Save VO
 *
 * @author Roy
 * @date 2020/7/9 18:57
 */
public interface ISaveService<S> {

    /**
     * 保存记录, 返回主键ID
     *
     * @param saveVO VO
     * @return Integer
     */
    Integer save(S saveVO);

    /**
     * 批量保存记录
     *
     * @param list list
     * @return Boolean
     */
    Boolean saveBatch(Collection<S> list);
}
