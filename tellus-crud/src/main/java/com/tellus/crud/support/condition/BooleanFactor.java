package com.tellus.crud.support.condition;

import com.tellus.support.enums.basic.OptionType;
import lombok.Builder;
import lombok.Data;

/**
 * 布尔条件因子 (仅支持 = EQ)
 *
 * @author Roy
 * @date 2020/5/26 0:08
 */
@Builder
public class BooleanFactor extends AbstractFactor {

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.EQ;
    }
}
