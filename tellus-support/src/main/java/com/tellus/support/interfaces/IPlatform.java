package com.tellus.support.interfaces;

/**
 * 用户所属平台
 *
 * @author Roy
 * @date 2020/5/18 17:45
 */
public interface IPlatform {

    /**
     * 获取平台ID
     *
     * @return 平台
     */
    Integer getPlatformId();

    /**
     * 设置平台ID
     *
     * @param platformId 平台ID
     */
    void setPlatformId(Integer platformId);
}
