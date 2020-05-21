package com.tellus.support.interfaces;

import java.util.Set;

/**
 * 平台编号
 *
 * @author Roy
 * @date 2020/5/18 17:55
 */
public interface IPlatformCodes {

    /**
     * 获取平台编号
     *
     * @return 平台编号
     */
    Set<String> getPlatformCodes();

    /**
     * 设置平台编号
     *
     * @param platformCodes 平台ID
     */
    void setPlatformCodes(Set<Integer> platformCodes);

}
