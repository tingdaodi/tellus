package com.tellus.toolkit.tree;

import com.tellus.support.interfaces.ISubordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建链表结构
 *
 * @author Roy
 * @date 2020/5/18 21:42
 */
public class NodeBuilder<K extends Serializable, V extends ISubordinate> {

    private final Map<K, Node<V>> cache = new HashMap<>();
    private final Map<K, V> indexMap = new HashMap<>();
    private final Map<K, V> sourceIndex = new HashMap<>();
    private IRelation<K, V> relation;
    private Node<V> rootNode;

    public NodeBuilder() {
    }

    public NodeBuilder<K, V> index(Iterable<V> resources) {
        for (V resource : resources) {
            sourceIndex.put(relation.getId(resource), resource);
        }
        return this;
    }

    public NodeBuilder<K, V> relation(IRelation<K, V> relation) {
        this.relation = relation;
        return this;
    }

    public List<Node<V>> toNode(Iterable<V> iterable) {
        //  根节点
        rootNode = new Node<>();

        //  清理缓存
        indexMap.clear();

        //  完整源数据
        indexMap.putAll(sourceIndex);

        //  当前数据源加入初始化数据中
        for (V v : iterable) {
            indexMap.put(relation.getId(v), v);
        }

        //  创建节点，确立与上下级的关系
        for (V v : iterable) {
            builderRelation(relation.getId(v), null);
        }

        //  返回树结构数据
        return rootNode.getChildren();
    }

    private void builderRelation(K k, Node<V> child) {

        //  保存至缓存
        if (!cache.containsKey(k)) {
            Node<V> node = new Node<>();
            node.setSource(indexMap.get(k));
            node.setChildren(new ArrayList<>());
            cache.put(k, node);
        }

        //  将 child 添加为 K 节点的子级
        Node<V> node = cache.get(k);
        if (null != child) {
            node.addChild(child);
        }

        //  无父级ID，或者在源数据中不存在，加入到根节点下
        K parentId = this.relation.getParentId(node.getSource());
        if (null == parentId || null == indexMap.get(parentId)) {
            if (!rootNode.getChildren().contains(node)) {
                node.setRoot(true);
                rootNode.addChild(node);
            }
            return;
        }

        //  获取 Child 的父节点
        Node<V> parentNode = cache.get(parentId);
        if (null == parentNode) {
            builderRelation(parentId, node);
        } else {
            if (!parentNode.getChildren().contains(node)) {
                parentNode.addChild(node);
                node.setParent(parentId);
            }
        }
    }

}
