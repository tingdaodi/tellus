package com.tellus.support;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序元素载体
 *
 * @author Roy
 * @date 2020/5/28 0:31
 */
@Data
@Accessors(chain = true)
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = -3540801745412875979L;

    /**
     * 需要进行排序的字段
     */
    private String column;

    /**
     * 是否正序排列，默认 true
     */
    private boolean asc = true;

    public static Order asc(String column) {
        return build(column, true);
    }

    public static Order desc(String column) {
        return build(column, false);
    }

    public static List<Order> ascs(String... columns) {
        return Arrays.stream(columns).map(Order::asc).collect(Collectors.toList());
    }

    public static List<Order> descs(String... columns) {
        return Arrays.stream(columns).map(Order::desc).collect(Collectors.toList());
    }

    private static Order build(String column, boolean asc) {
        Order item = new Order();
        item.setColumn(column);
        item.setAsc(asc);
        return item;
    }

}
