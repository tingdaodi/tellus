package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateResourceVO;
import com.tellus.support.model.vo.result.ResourceVO;
import com.tellus.support.model.vo.retrieve.RetrieveResourceVO;
import com.tellus.support.model.vo.update.UpdateResourceVO;

/**
 * 资源服务类
 *
 * @author Roy
 * @date 2020/7/13 12:22
 */
public interface IResourceService extends IBasicRelationService<ResourceVO,
        CreateResourceVO, RetrieveResourceVO, UpdateResourceVO> {

}
