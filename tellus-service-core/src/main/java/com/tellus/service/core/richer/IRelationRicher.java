package com.tellus.service.core.richer;

import com.tellus.service.core.service.single.RelationService;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.exception.SystemErrorException;

/**
 * 关系充血模型接口
 *
 * @author Roy
 * @date 2020/7/1 1:28
 */
public interface IRelationRicher {

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
     * 必要的初始化
     */
    void initNeed();

    /**
     * 保存节点关系 (新增节点)
     *
     * @param target 目标节点
     */
    void saveTo(int target);

    /**
     * 将一个分类移动到目标分类下面（成为其子分类）。被移动分类的子类将自动上浮（成为指定分类
     * 父类的子分类），即使目标是指定分类原本的父类。
     * <p>
     * 例如下图(省略顶级分类)：
     * 1                                    1
     * |                                  / | \
     * 2                                 3  4  5
     * / | \         (id=2).moveTo(7)         / \
     * 3  4  5       ----------------->      6   7
     * / \                                  /  / | \
     * 6    7                              8  9  10 2
     * /    /  \
     * 8    9    10
     *
     * @param target 目标节点
     * @throws SystemErrorException 如果target所表示的分类不存在、或此分类的id==target
     */
    void moveTo(int target);

    /**
     * 将一个分类移动到目标分类下面（成为其子分类），被移动分类的子分类也会随着移动。
     * 如果目标分类是被移动分类的子类，则先将目标分类（连带子类）移动到被移动分类原来的
     * 的位置，再移动需要被移动的分类。
     * <p>
     * 例如下图(省略顶级分类)：
     * 1                                      1
     * |                                      |
     * 2                                      7
     * / | \        (id=2).moveTreeTo(7)    / | \
     * 3  4  5      --------------------> 9  10  2
     * / \                                     / | \
     * 6    7                                 3  4  5
     * /    /  \                              |
     * 8    9    10                           6
     *
     * @param target 目标节点
     * @throws SystemErrorException 如果id或target所表示的分类不存在、或id==target
     */
    void moveTreeTo(int target);

    /**
     * 将指定节点的所有子树移动到某节点下
     * 如果两个参数相同，则相当于重建子树，用于父节点移动后更新路径
     * <p>
     * 例如下图(省略顶级分类)：
     * 1                                      1
     * |                                      |
     * 2                                      2
     * / | \        (id=3).moveSubTree(5)    / | \
     * 3  4  5      -------------------->  3  4  5
     * / \                                      / \
     * 6  7                                    6  7
     * / /  \                                 /  /  \
     * 8 9  10                               8  9  10
     *
     * @param target 目标节点
     */
    void moveSubTree(int target);

    // ~ Setter
    // ==============================================================================

    /**
     * 当前节点
     *
     * @param descendant 当前节点
     */
    void setDescendant(Integer descendant);

    /**
     * 关系类型
     *
     * @param relationType 关系类型
     */
    void setRelationType(RelationTypeEnum relationType);

    /**
     * 是否支持仅移动当前节点
     * 其下级节点自动上浮
     *
     * @return boolean
     */
    Boolean isMoveOnlyCurrentNodeSupported();

    /**
     * 是否支持父级节点移动至其下级节点
     *
     * @return boolean
     */
    Boolean isMoveToSubSupported();

    /**
     * 关系数据服务
     *
     * @param relationService 关系数据服务
     */
    void setRelationService(RelationService relationService);

}
