package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.CaseFormatKit;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 集合条件因子
 *
 * @author Roy
 * @date 2020/5/26 10:57
 */
public class SetFactor extends AbstractFactor {

    @SneakyThrows
    @Override
    public <T> void handle(QueryWrapper<T> wrapper, Class<T> cls) {
        if (null == value) {
            return;
        }

        Collection<?> values = (Collection<?>) value;
        if (values.isEmpty()) {
            return;
        }

        Set<Object> set = new HashSet<>();
        for (Object o : values) {
            if (o.getClass().isEnum()) {
                Method method = FactorKit.getEnumValueMethod(o.getClass());
                method.setAccessible(true);
                Object code = method.invoke(o);
                set.add(code);
            } else {
                set.add(o);
            }
        }

        String column = CaseFormatKit.toUpperUnderscore(fieldName);
        switch (optionType) {
            case IN:
                wrapper.in(column, set);
                break;
            case NOT_IN:
                wrapper.notIn(column, set);
                break;
            default:
        }
    }

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.IN
                || optionType == OptionType.NOT_IN;
    }
}
