package com.tellus.permission.cloud.controller;

import com.tellus.permission.cloud.support.IBasicRelationService;

/**
 * 层级关系通用 Controller
 *
 * @author Roy
 * @date 2020/7/16 10:31
 */
public abstract class AbstractRelationController<V, S, R, U> extends BaseRelationController {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IBasicRelationService<V, S, R, U> basicRelationService;

    // ~ Constructors
    // ==============================================================================

    public AbstractRelationController(IBasicRelationService<V, S, R, U> basicRelationService) {
        this.basicRelationService = basicRelationService;
    }


}
