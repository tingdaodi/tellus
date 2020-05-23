package com.tellus.toolkit;

import com.tellus.support.interfaces.ISubordinate;
import com.tellus.toolkit.tree.IRelation;

/**
 * 关系工具类
 *
 * @author Roy
 * @date 2020/5/18 21:10
 */
public class RelationKit {

    public static final Integer ROOT = 0;

    public static Boolean whetherRoot(Integer nodeId) {
        return ROOT.equals(nodeId);
    }

    public static <V extends ISubordinate> IRelation<Integer, V> getRelation() {
        return new IRelation<Integer, V>() {
            @Override
            public Integer getParentId(V t) {
                if (null == t.getParentId() || t.getParentId() == 0 || t.getId().equals(t.getParentId())) {
                    return null;
                }
                return t.getParentId();
            }

            @Override
            public Integer getId(V t) {
                return t.getId();
            }
        };
    }

}
