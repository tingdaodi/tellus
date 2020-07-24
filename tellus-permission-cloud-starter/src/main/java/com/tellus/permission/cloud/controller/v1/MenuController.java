package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractRelationController;
import com.tellus.permission.cloud.service.IMenuService;
import com.tellus.support.Result;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.create.CreateMenuVO;
import com.tellus.support.model.vo.result.MenuVO;
import com.tellus.support.model.vo.retrieve.RetrieveMenuVO;
import com.tellus.support.model.vo.update.UpdateMenuVO;
import com.tellus.toolkit.tree.Node;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Roy
 * @date 2020/7/18 18:46
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/menus")
@Api(tags = "菜单管理")
public class MenuController extends AbstractRelationController<MenuVO, CreateMenuVO, RetrieveMenuVO, UpdateMenuVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IMenuService menuService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public MenuController(IMenuService menuService) {
        super(menuService);
        this.menuService = menuService;
    }

    // ~ Main Methods
    // ==============================================================================


    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================

    @Override
    protected RelationTypeEnum getRelationType() {
        return RelationTypeEnum.MENU;
    }


    // ~ Private Methods
    // ==============================================================================
}
