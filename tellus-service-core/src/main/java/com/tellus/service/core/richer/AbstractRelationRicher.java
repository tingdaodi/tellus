package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.enums.SystemErrorCodeEnum;
import com.tellus.support.exception.NotMatchException;
import com.tellus.toolkit.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 层级关系充血模型抽象类
 *
 * @author Roy
 * @date 2020/7/1 12:25
 */
@Slf4j
public abstract class AbstractRelationRicher implements IRelationRicher {

    // ~ Static fields/initializers
    // ==============================================================================

    protected Integer descendant;
    protected RelationTypeEnum relationType;
    protected Boolean moveOnlyCurrentNodeSupported;
    protected Boolean moveToSubSupported;
    protected RelationService relationService;

    // ~ Pubic Methods
    // ==============================================================================

    public boolean isRoot() {
        return descendant.equals(ROOT);
    }

    public Integer getDistant(int ancestor, int descendant) {
        return relationService.findDistance(relationType, ancestor, descendant);
    }

    public Integer getDirectParent() {
        return relationService.findDirectParent(relationType, descendant);
    }

    // ~ Override/Protected Methods
    // ==============================================================================

    @Override
    public void initNeed() {
        if (null == relationService) {
            throw ExceptionUtils.mpe("Must be injected: com.tellus.service.core.service.single.RelationService");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTo(int target) {
        checkIsRoot();
        checkItSelf(target);

        //  当前节点数据是否已存在
        boolean result = relationService.findSubsByDistance(relationType, descendant, 0).size() >= 1;
        if (result) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_SAVE_NODE_EXISTS,
                    () -> "Move to target[" + target
                            + "] relationType[" + relationType
                            + "], descendant[" + descendant + "] already exists");
        }

        //  保存节点数据
        moveNode(descendant, target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveTo(int target) {
        checkIsRoot();
        checkItSelf(target);
        checkTargetExist(target);

        //  是否支持移动节点
        if (!isMoveOnlyCurrentNodeSupported()) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_NOT_SUPPORTED_MOVE_NODE,
                    () -> "Not support move, relationType["
                            + relationType + "] target[" + target + "] descendant[" + descendant + "]");
        }
        //  当前节点是否已是上下级关系
        boolean targetHasSub = relationService.findSubs(relationType, target).contains(descendant);
        if (targetHasSub) {
            //  是否支持移动至下级节点
            if (!isMoveToSubSupported()) {
                throw new NotMatchException(SystemErrorCodeEnum.RELATION_NOT_SUPPORTED_MOVE_TO_SUB,
                        () -> "Move to target[" + target
                                + "] relationType[" + relationType
                                + "] descendant[" + descendant + "], Does not support moving to lower nodes");
            }
        }

        //  当前节点的直属上级
        Integer parent = getDirectParent();
        //  1. 无上级, 新增节点, 无下级可移动
        //  2. 有上级, 将当前节点的所有下级节点移动至 #{parent} 节点下
        if (null != parent) {
            moveSubs(descendant, target);
        }
        //  移动当前节点至 #{target} 下
        moveNode(descendant, target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveTreeTo(int target) {
        checkIsRoot();
        checkItSelf(target);
        checkTargetExist(target);

        //  当前节点是否已是上下级关系
        boolean targetHasSub = relationService.findSubs(relationType, target).contains(descendant);
        if (targetHasSub) {
            //  是否支持移动至下级节点
            if (!isMoveToSubSupported()) {
                throw new NotMatchException(SystemErrorCodeEnum.RELATION_NOT_SUPPORTED_MOVE_TO_SUB,
                        () -> "Move to target[" + target
                                + "] relationType[" + relationType
                                + "] descendant[" + descendant + "], Does not support moving to lower nodes");
            }

            //  1. 将当前节点的子级, 上浮至当前节点的直属上级下
            //  2. 重建目标节点分支路径
            Integer parent = getDirectParent();
            moveNode(target, parent);
            moveSubs(target, target);
        }

        //  1. 当前节点至目标节点下
        //  2. 重建当前节点分支路径
        moveNode(descendant, target);
        moveSubs(descendant, descendant);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveSubTree(int target) {
        moveSubs(descendant, target);
    }

    // ~ Private Methods
    // ==============================================================================

    private void moveNode(int descendant, int ancestor) {
        if (isRoot()) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_NOT_MOVE_ROOT);
        }

        //  1. 删除原关系
        relationService.removePath(relationType, descendant);
        //  2. 将当前节点保存到新节点下
        relationService.savePath(relationType, ancestor, descendant);
        //  3. 保存节点自身
        relationService.saveNode(relationType, descendant);
    }

    /**
     * 将 #{descendant} 的所有子级移动到节点 #{ancestor} 下
     * 当 {@code descendant == ancestor} 时, 重建子树, 父节点移动后重建路径
     *
     * @param descendant 下级节点
     * @param ancestor   上级节点
     */
    private void moveSubs(int descendant, int ancestor) {
        relationService.findDirectSubs(relationType, descendant)
                .forEach(node -> {
                    moveNode(node, ancestor);
                    moveSubs(node, node);
                });
    }

    private void checkIsRoot() {
        if (isRoot()) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_NOT_MOVE_ROOT);
        }
    }

    private void checkItSelf(Integer target) {
        //  当前节点与目标节点相同
        if (descendant.equals(target)) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_MOVE_TARGET_NODE_SAME,
                    () -> "Cant move to the node itself, relationType["
                            + relationType + "] target[" + target + "]");
        }
    }

    private void checkTargetExist(Integer target) {
        boolean result = relationService.findSubsByDistance(relationType, target, 0).size() >= 1;
        if (!result) {
            throw new NotMatchException(SystemErrorCodeEnum.RELATION_SAVE_NODE_EXISTS,
                    () -> "Move to target[" + target
                            + "] relationType[" + relationType
                            + "], descendant[" + descendant + "] does not exists");
        }
    }

    // ~ Setting Methods
    // ==============================================================================

    @Override
    public void setDescendant(Integer descendant) {
        this.descendant = descendant;
    }


    @Override
    public void setRelationType(RelationTypeEnum relationType) {
        this.relationType = relationType;
    }

    @Override
    public Boolean isMoveOnlyCurrentNodeSupported() {
        return moveOnlyCurrentNodeSupported;
    }

    public void setMoveOnlyCurrentNodeSupported(Boolean moveOnlyCurrentNodeSupported) {
        this.moveOnlyCurrentNodeSupported = moveOnlyCurrentNodeSupported;
    }

    @Override
    public Boolean isMoveToSubSupported() {
        return moveToSubSupported;
    }

    public void setMoveToSubSupported(Boolean moveToSubSupported) {
        this.moveToSubSupported = moveToSubSupported;
    }

    @Override
    public void setRelationService(RelationService relationService) {
        this.relationService = relationService;
    }
}
