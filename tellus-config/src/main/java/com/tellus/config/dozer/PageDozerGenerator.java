package com.tellus.config.dozer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;

/**
 * Dozer 深度复制工具 (分页)
 *
 * @author Roy
 * @date 2020/5/21 12:59
 */
public class PageDozerGenerator extends DozerGenerator implements IPageHelperGenerator {

    @Override
    public <T, V, S extends IPage<T>> PageWrapper<V> convert(S src, Class<V> clz) {
        return new PageWrapper<>(src.getTotal(), src.getSize(), src.getCurrent(), this.convert(src.getRecords(), clz));
    }

    @Override
    public <T, V, S extends PageWrapper<T>> PageWrapper<V> convert(S wrapper, Class<V> clz) {
        return new PageWrapper<>(wrapper.getTotal(), wrapper.getSize(), wrapper.getCurrent(),
                this.convert(wrapper.getRecords(), clz));
    }

    @Override
    public <T, V, S extends PageInfo<V>> PageInfo<V> convert(S pageInfo, Class<V> clz) {
        return new PageInfo<>(pageInfo.getSize(), pageInfo.getCurrent(),
                this.convert(pageInfo.getQueries(), clz), pageInfo.getAsc(), pageInfo.getDesc());
    }
}
