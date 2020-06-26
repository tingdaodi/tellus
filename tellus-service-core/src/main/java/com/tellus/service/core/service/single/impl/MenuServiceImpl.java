package com.tellus.service.core.service.single.impl;

import com.tellus.crud.service.ICustomizeRelationServiceImpl;
import com.tellus.service.core.mapper.MenuMapper;
import com.tellus.service.core.model.MenuEntity;
import com.tellus.service.core.service.single.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 菜单表 服务实现类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
@Service
public class MenuServiceImpl extends ICustomizeRelationServiceImpl<MenuMapper, MenuEntity> implements MenuService {

}
