package com.tellus.service.core.service.single.impl;

import com.tellus.service.core.model.MenuEntity;
import com.tellus.service.core.mapper.MenuMapper;
import com.tellus.service.core.service.single.MenuService;
import com.tellus.crud.service.ICustomizeServiceImpl;
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
public class MenuServiceImpl extends ICustomizeServiceImpl<MenuMapper, MenuEntity> implements MenuService {

}
