package com.tellus.permission.cloud.controller;

import com.tellus.permission.oauth2.service.AuthorizationService;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.interfaces.IAncestor;
import com.tellus.support.interfaces.IDescendant;

import javax.annotation.Resource;

/**
 * 层级关系 controller , 提供底层通用方式实现
 *
 * @author Roy
 * @date 2020/7/13 16:44
 */
public abstract class BaseRelationController extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    @Resource
    private AuthorizationService authorizationService;

    // ~ Protected Methods
    // ==============================================================================

    /**
     * 校验数据权限
     *
     * @param vo  VO
     * @param <V> V
     */
    protected <V> void checkPermission(V vo) {
        //  是否拥有操作的上级资源
        if (vo instanceof IAncestor) {
            IAncestor ancestor = (IAncestor) vo;
            if (null != ancestor.getAncestor()) {
                RelationTypeEnum relationType = getRelationType();
                authorizationService.isSubordinate(relationType, ancestor.getAncestor());
            }
        }

        //  是否拥有操作的下级资源
        if (vo instanceof IDescendant) {
            IDescendant descendant = (IDescendant) vo;
            if (null != descendant.getId()) {

            }
        }

    }

    protected void checkWhetherSubordinate(RelationTypeEnum relationType, Integer nodeId) {

    }

    /**
     * 获取关系类型
     *
     * @return RelationTypeEnum
     */
    protected abstract RelationTypeEnum getRelationType();

    // ~ Public Methods
    // ==============================================================================


}
