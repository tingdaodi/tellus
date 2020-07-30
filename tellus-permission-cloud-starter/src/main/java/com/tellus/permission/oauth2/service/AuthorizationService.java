package com.tellus.permission.oauth2.service;

import com.google.common.collect.Sets;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.support.model.vo.result.ResourceVO;

import java.util.List;
import java.util.Set;

/**
 * 授权业务接口类
 *
 * @author Roy
 * @date 2020/7/6 13:59
 */
public interface AuthorizationService {

    /**
     * 查询用户信息
     *
     * @param username  用户名
     * @param platforms 平台编号
     * @return CustomizeUserDetails
     */
    CustomizeUserDetails loadUserByUsername(String username, Set<String> platforms);

    /**
     * 查询用户信息
     *
     * @param username 用户名
     * @return CustomizeUserDetails
     */
    CustomizeUserDetails loadUserByUsername(String username);

    /**
     * 查询所有的资源
     *
     * @return List<ResourceVO>
     */
    List<ResourceVO> findAllResources();

    /**
     * 校验 #{descendant} 是否为当前用户的下级
     *
     * @param relationType 关系类型
     * @param descendant   下级节点
     * @return Boolean
     */
    default Boolean isSubordinate(RelationTypeEnum relationType, Integer descendant) {
        return isSubordinate(relationType, Sets.newHashSet(descendant));
    }

    /**
     * 校验 #{descendant} 是否为当前用户的下级
     *
     * @param relationType 关系类型
     * @param descendants  下级节点
     * @return Boolean
     */
    Boolean isSubordinate(RelationTypeEnum relationType, Set<Integer> descendants);

    /**
     * 登录成功后, 记录登录日志, 并更新用户最后登录时间/IP
     *
     * @param createLoginLogVO 创建日志
     * @return Boolean
     */
    Boolean loginSuccessAfter(CreateLoginLogVO createLoginLogVO);

}
