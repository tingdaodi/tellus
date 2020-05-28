package com.tellus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tellus.crud.support.PageUtil;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.toolkit.ReflectionKit;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * 查询地城通用 controller
 *
 * @author Roy
 * @date 2020/5/28 12:54
 */
@Validated
public abstract class AbstractRetrieveController<T, V, R> extends AbstractBasicController<T> {

    // ~ Static fields/initializers
    // ==============================================================================


    // ~ Request Methods
    // ==============================================================================

    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询记录", notes = "分页查询记录通用 Restful API")
    public PageWrapper<V> page(@Valid @RequestBody PageInfo<R> pageIno) {
        //  构建 wrapper 对象
        QueryWrapper<T> wrapper = this.builderWrapper(pageIno.getQueries(), getEntityClass());

        IPage<T> result = this.customizeService
                .page(PageUtil.builderOrderUpperUnderscore(pageIno), wrapper);

        return this.dozerGenerator.convert(result, getVoClass());
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询列表记录", notes = "查询列表记录通用 Restful API")
    public List<V> list(@Valid R retrieveVO) {
        QueryWrapper<T> wrapper = this.builderWrapper(retrieveVO, getEntityClass());
        return this.dozerGenerator.convert(this.customizeService.list(wrapper), getVoClass());
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计总数", notes = "根据条件统计总记录数 Restful API")
    public Integer count(@Valid R retrieveVO) {
        QueryWrapper<T> wrapper = this.builderWrapper(retrieveVO, getEntityClass());
        return this.customizeService.count(wrapper);
    }

    // ~ Override/Protected Methods
    // ==============================================================================

    @Override
    protected Class<T> getEntityClass() {
        //noinspection unchecked
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    protected Class<V> getVoClass() {
        //noinspection unchecked
        return (Class<V>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }

    // ~ Private Methods
    // ==============================================================================

}
