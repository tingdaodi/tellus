package com.tellus.web.service;

import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.update.MoveRelationVO;

/**
 * 层级关系移动策略接口
 *
 * @author Roy
 * @date 2020/7/1 18:36
 */
public interface RelationStrategyService {

    /**
     * 根节点
     */
    Integer ROOT = 0;
    /**
     * 直属层级
     */
    Integer CURRENT_DISTANCE = 0;
    /**
     * 直属层级
     */
    Integer DIRECT_DISTANCE = 1;

    /**
     * 移除节点, 先将待移除节点移动至ROOT节点下, 然后再删除
     *
     * @param moveRelationVO 移动实体
     */
    void remove(MoveRelationVO moveRelationVO);

    /**
     * 移动节点
     *
     * @param moveRelationVO 移动实体
     */
    default void move(MoveRelationVO moveRelationVO) {
        //  isInstantMove  即时移动
        //  isSaveRecord   是否保存记录
        this.move(moveRelationVO, Boolean.TRUE, Boolean.TRUE);
    }

    /**
     * 移动节点
     *
     * @param moveRelationVO 移动实体
     * @param isInstantMove  即时移动
     * @param isSaveRecord   是否保存记录
     */
    void move(MoveRelationVO moveRelationVO, Boolean isInstantMove, Boolean isSaveRecord);

    /**
     * 保存资源, 建立关系
     *
     * @param relationType 关系类型
     * @param entity       资源实体
     * @return 资源ID
     */
    <T> Integer saveAndRelation(RelationTypeEnum relationType, T entity);
}
