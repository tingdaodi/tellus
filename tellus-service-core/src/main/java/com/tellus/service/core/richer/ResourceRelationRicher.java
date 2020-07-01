package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;

/**
 * 组织关系模型
 *
 * @author Roy
 * @date 2020/7/1 17:45
 */
@IRicher(value = RelationTypeEnum.RESOURCE)
public class ResourceRelationRicher extends AbstractRelationRicher {

    public ResourceRelationRicher() {
        this.setRelationType(RelationTypeEnum.RESOURCE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public ResourceRelationRicher(Integer descendant) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.RESOURCE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public ResourceRelationRicher(Integer descendant,
                                  RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.RESOURCE);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public ResourceRelationRicher(Integer descendant,
                                  Boolean moveOnlyCurrentNodeSupported,
                                  Boolean moveToSubSupported) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.RESOURCE);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

    public ResourceRelationRicher(Integer descendant,
                                  Boolean moveOnlyCurrentNodeSupported,
                                  Boolean moveToSubSupported,
                                  RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.RESOURCE);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

}
