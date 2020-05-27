package com.tellus.crud.support.condition;

import com.tellus.support.enums.basic.OptionType;

/**
 * 布尔条件因子 (仅支持 = EQ)
 *
 * @author Roy
 * @date 2020/5/26 0:08
 */
public class BooleanFactor extends AbstractFactor {

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.EQ;
    }
}
