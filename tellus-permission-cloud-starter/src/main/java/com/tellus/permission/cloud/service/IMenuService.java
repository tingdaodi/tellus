package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateMenuVO;
import com.tellus.support.model.vo.result.MenuVO;
import com.tellus.support.model.vo.retrieve.RetrieveMenuVO;
import com.tellus.support.model.vo.update.UpdateMenuVO;

/**
 * 菜单服务类
 *
 * @author Roy
 * @date 2020/7/10 19:08
 */
public interface IMenuService extends IBasicRelationService<MenuVO, CreateMenuVO, RetrieveMenuVO, UpdateMenuVO> {

}
