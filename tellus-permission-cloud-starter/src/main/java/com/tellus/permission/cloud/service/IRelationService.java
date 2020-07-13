package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IRetrieveService;
import com.tellus.support.model.vo.result.RelationVO;
import com.tellus.support.model.vo.retrieve.RetrieveRelationVO;

/**
 * 层级关系服务类
 *
 * @author Roy
 * @date 2020/7/13 12:17
 */
public interface IRelationService extends IRetrieveService<RelationVO, RetrieveRelationVO> {

    /**
     * 根节点
     */
    Integer ROOT = 0;
    /**
     * 直属层级
     */
    Integer DIRECT_DISTANCE = 1;


}
