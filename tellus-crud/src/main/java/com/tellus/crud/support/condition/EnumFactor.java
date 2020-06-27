package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.CaseFormatKit;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

/**
 * 枚举条件因子 (仅支持 =EQ)
 *
 * @author Roy
 * @date 2020/5/26 10:09
 */
public class EnumFactor extends AbstractFactor {

    @SneakyThrows
    @Override
    public <T> void handle(QueryWrapper<T> wrapper) {
        if (null == value) {
            return;
        }

        Method method = FactorKit.getEnumValueMethod(value.getClass());
        method.setAccessible(true);
        Object enumValue = method.invoke(value);

        if (null != enumValue) {
            String column = CaseFormatKit.toUpperUnderscore(fieldName);
            wrapper.eq(column, enumValue);
        }
    }

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.EQ;
    }
}
