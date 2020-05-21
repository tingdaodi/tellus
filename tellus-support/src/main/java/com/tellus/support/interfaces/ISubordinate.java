package com.tellus.support.interfaces;

import java.io.Serializable;

/**
 * 从属关系
 *
 * @author Roy
 * @date 2020/5/18 17:59
 */
public interface ISubordinate extends Serializable {

    Integer ROOT = 0;

    /**
     * 直属上级
     *
     * @return 直属上级
     */
    Integer getParentId();

    /**
     * 当前ID
     *
     * @return 当前ID
     */
    Integer getId();

}
