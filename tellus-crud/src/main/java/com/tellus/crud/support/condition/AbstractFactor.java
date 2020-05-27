package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.CaseFormatKit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 条件因子抽象类
 *
 * @author Roy
 * @date 2020/5/26 0:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFactor implements Factor {

    protected String fieldName;
    protected OptionType optionType;
    protected Object value;

    @Override
    public <T> void handle(QueryWrapper<T> wrapper, Class<T> cls) {
        if (null == value) {
            return;
        }
        String column = CaseFormatKit.toUpperUnderscore(fieldName);
        wrapper.eq(column, value);
    }
}
