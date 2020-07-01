package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;

/**
 * 组织关系模型
 *
 * @author Roy
 * @date 2020/7/1 17:45
 */
@IRicher(value = RelationTypeEnum.MENU)
public class MenuRelationRicher extends AbstractRelationRicher {

    public MenuRelationRicher() {
        this.setRelationType(RelationTypeEnum.MENU);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public MenuRelationRicher(Integer descendant) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.MENU);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public MenuRelationRicher(Integer descendant,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.MENU);
        this.setMoveOnlyCurrentNodeSupported(false);
        this.setMoveToSubSupported(false);
    }

    public MenuRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported) {
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.MENU);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

    public MenuRelationRicher(Integer descendant,
                              Boolean moveOnlyCurrentNodeSupported,
                              Boolean moveToSubSupported,
                              RelationService relationService) {
        this.setRelationService(relationService);
        this.setDescendant(descendant);
        this.setRelationType(RelationTypeEnum.MENU);
        this.setMoveOnlyCurrentNodeSupported(moveOnlyCurrentNodeSupported);
        this.setMoveToSubSupported(moveToSubSupported);
    }

}
