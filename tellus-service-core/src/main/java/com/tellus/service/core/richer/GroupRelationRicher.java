package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;

/**
 * 组织关系模型
 *
 * @author Roy
 * @date 2020/7/1 17:45
 */
@IRicher(value = RelationTypeEnum.GROUP)
public class GroupRelationRicher extends AbstractRelationRicher {

    public GroupRelationRicher() {
        this.setRelationType(RelationTypeEnum.GROUP);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public GroupRelationRicher(Integer descendant) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.GROUP);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public GroupRelationRicher(Integer descendant,
                               RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.GROUP);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public GroupRelationRicher(Integer descendant,
                               Boolean moveOnlyCurrentNodeSupported,
                               Boolean moveToSubSupported) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.GROUP);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

    public GroupRelationRicher(Integer descendant,
                               Boolean moveOnlyCurrentNodeSupported,
                               Boolean moveToSubSupported,
                               RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.GROUP);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

}
