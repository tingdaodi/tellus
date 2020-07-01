package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;

/**
 * 组织关系模型
 *
 * @author Roy
 * @date 2020/7/1 17:45
 */
@IRicher(value = RelationTypeEnum.USER)
public class UserRelationRicher extends AbstractRelationRicher {

    public UserRelationRicher() {
        this.setRelationType(RelationTypeEnum.USER);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public UserRelationRicher(Integer descendant) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.USER);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public UserRelationRicher(Integer descendant,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.USER);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public UserRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.USER);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

    public UserRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.USER);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

}
