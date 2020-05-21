package com.tellus.toolkit.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 链表结构
 *
 * @author Roy
 * @date 2020/5/18 21:42
 */

@Data
@NoArgsConstructor
public class Node<V extends Serializable> implements Serializable {
    private static final long serialVersionUID = 3473607708248934190L;

    /**
     * 是否为根节点
     */
    private boolean root;
    /**
     * 是否有子级
     */
    private boolean leaf;
    /**
     * 当前节点信息
     */
    private V source;
    /**
     * 直属上级ID
     */
    private Object parentId;

    /**
     * 子节点信息
     */
    private List<Node<V>> children = Collections.emptyList();

    /**
     * 添加子节点
     *
     * @param node 节点
     */
    public void addChild(Node<V> node) {
        setLeaf(true);
        this.children.add(node);
    }

    /**
     * 设置父节点
     *
     * @param parentId 父节点
     */
    public void setParent(Object parentId) {
        this.parentId = parentId;
    }

}
