package com.tellus.web.service.impl;

import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.update.MoveRelationVO;
import com.tellus.web.service.RelationStrategyService;
import org.springframework.stereotype.Service;

/**
 * 层级关系移动策略接口
 *
 * @author Roy
 * @date 2020/7/1 19:09
 */
@Service
public class RelationStrategyServiceImpl implements RelationStrategyService {

    @Override
    public void remove(MoveRelationVO moveRelationVO) {

    }

    @Override
    public void move(MoveRelationVO moveRelationVO, Boolean isInstantMove, Boolean isSaveRecord) {

    }

    @Override
    public <T> Integer saveAndRelation(RelationTypeEnum relationType, T entity) {
        return null;
    }
}
