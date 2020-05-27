package com.tellus.crud.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tellus.support.PageInfo;
import com.tellus.toolkit.CaseFormatKit;

import java.util.Arrays;
import java.util.List;

/**
 * 分页工具类
 *
 * @author Roy
 * @date 2020/5/28 0:22
 */
public class PageUtils {

    /**
     * 将排序字段转换为大写下划线的格式
     *
     * @param pageInfo 分页参数
     * @param <S>      S
     * @param <T>      T
     * @return IPage<S>
     */
    public static <S, T> IPage<S> builderOrderUpperUnderscore(PageInfo<T> pageInfo) {
        Page<S> page = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        if (null != pageInfo.getAscs()) {
            List<OrderItem> orderItems = OrderItem.ascs(
                    Arrays.stream(pageInfo.getAscs())
                            .filter(s -> s.matches("^[\\w.]+$"))
                            .map(CaseFormatKit::toUpperUnderscore)
                            .toArray(String[]::new));
            page.addOrder(orderItems);
        }

        if (null != pageInfo.getDescs()) {
            List<OrderItem> orderItems = OrderItem.descs(
                    Arrays.stream(pageInfo.getDescs())
                            .filter(s -> s.matches("^[\\w.]+$"))
                            .map(CaseFormatKit::toUpperUnderscore)
                            .toArray(String[]::new));
            page.addOrder(orderItems);
        }

        return page;
    }

}
