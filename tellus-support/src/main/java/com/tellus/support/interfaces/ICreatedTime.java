package com.tellus.support.interfaces;

import java.time.LocalDateTime;

/**
 * 自动注入创建人，创建时间
 *
 * @author Roy
 * @date 2020/5/18 17:27
 */
public interface ICreatedTime {

    /**
     * 创建人
     *
     * @param creator 创建人
     */
    void setCreator(String creator);

    /**
     * 创建时间
     *
     * @param createdAt 创建时间
     */
    void setCreatedAt(LocalDateTime createdAt);

}
