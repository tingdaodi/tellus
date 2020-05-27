package com.tellus.crud.controller;

import com.tellus.config.dozer.PageDozerGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础 Controller 提供地城通用实现
 *
 * @author Roy
 * @date 2020/5/25 23:53
 */
public class BasicController {

    // ~ Static fields/initializers
    // ==============================================================================

    @Autowired
    protected PageDozerGenerator dozerGenerator;


    protected <V, T> T convert(V vo, Class<T> entityClass) {
        return this.dozerGenerator.convert(vo, entityClass);
    }

}
