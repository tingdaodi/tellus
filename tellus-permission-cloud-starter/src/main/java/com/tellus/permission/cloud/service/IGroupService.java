package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateGroupVO;
import com.tellus.support.model.vo.result.GroupVO;
import com.tellus.support.model.vo.retrieve.RetrieveGroupVO;
import com.tellus.support.model.vo.update.UpdateGroupVO;

/**
 * 组织服务类
 *
 * @author Roy
 * @date 2020/7/10 16:43
 */
public interface IGroupService extends IBasicRelationService<GroupVO, CreateGroupVO, RetrieveGroupVO, UpdateGroupVO> {

}
