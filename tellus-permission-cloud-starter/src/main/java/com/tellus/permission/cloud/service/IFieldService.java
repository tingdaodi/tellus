package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicService;
import com.tellus.support.model.vo.create.CreateFieldVO;
import com.tellus.support.model.vo.result.FieldVO;
import com.tellus.support.model.vo.retrieve.RetrieveFieldVO;
import com.tellus.support.model.vo.update.UpdateFieldVO;

/**
 * 字段资源服务类
 *
 * @author Roy
 * @date 2020/7/9 18:56
 */
public interface IFieldService extends IBasicService<FieldVO, CreateFieldVO, RetrieveFieldVO, UpdateFieldVO> {


}
