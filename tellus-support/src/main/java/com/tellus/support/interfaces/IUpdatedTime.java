package com.tellus.support.interfaces;

import java.time.LocalDateTime;

/**
 * 自动注入更新人，更新时间
 *
 * @author Roy
 * @date 2020/5/18 17:27
 */
public interface IUpdatedTime {

    /**
     * 更新人
     *
     * @param updater 更新人
     */
    void setUpdate(String updater);

    /**
     * 更新时间
     *
     * @param updatedAt 更新时间
     */
    void setUpdatedAt(LocalDateTime updatedAt);

}
