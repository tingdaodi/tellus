package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;

/**
 * 组织关系模型
 *
 * @author Roy
 * @date 2020/7/1 17:45
 */
@IRicher(value = RelationTypeEnum.ROLE)
public class RoleRelationRicher extends AbstractRelationRicher {

    public RoleRelationRicher() {
        this.setRelationType(RelationTypeEnum.ROLE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public RoleRelationRicher(Integer descendant) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.ROLE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public RoleRelationRicher(Integer descendant,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.ROLE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public RoleRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.ROLE);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

    public RoleRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.ROLE);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

}
