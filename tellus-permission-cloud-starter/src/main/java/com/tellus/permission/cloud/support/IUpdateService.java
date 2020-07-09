package com.tellus.permission.cloud.support;

import java.io.Serializable;
import java.util.Collection;

/**
 * 更新记录服务接口
 * <p>
 * U - Update VO
 *
 * @author Roy
 * @date 2020/7/9 18:58
 */
public interface IUpdateService<U> {

    /**
     * 删除记录
     *
     * @param id ID
     * @return Boolean
     */
    Boolean removeById(Serializable id);

    /**
     * 批量删除记录
     *
     * @param ids IDS
     * @return Boolean
     */
    Boolean removeByIds(Collection<Serializable> ids);

    /**
     * 更新记录
     *
     * @param updateVO 更新VO
     * @return Boolean
     */
    Boolean updateById(U updateVO);

}
