package com.tellus.service.core.richer;

import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.exception.NotMatchException;

/**
 * 关系模型工厂类
 *
 * @author Roy
 * @date 2020/7/1 17:57
 */
public class RelationRicherFactory {

    public static IRelationRicher create(RelationTypeEnum relationType) {
        IRelationRicher relationRicher;
        switch (relationType) {
            case USER:
                relationRicher = new UserRelationRicher();
                break;
            case GROUP:
                relationRicher = new GroupRelationRicher();
                break;
            case ROLE:
                relationRicher = new RoleRelationRicher();
                break;
            case RESOURCE:
                relationRicher = new ResourceRelationRicher();
                break;
            case MENU:
                relationRicher = new MenuRelationRicher();
                break;
            default:
                throw new NotMatchException("未找到对应的模型");
        }
        return relationRicher;
    }

}
