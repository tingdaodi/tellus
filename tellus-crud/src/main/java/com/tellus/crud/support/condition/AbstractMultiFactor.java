package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.CaseFormatKit;
import lombok.Data;

/**
 * 多条件因子抽象类
 *
 * @author Roy
 * @date 2020/5/26 9:42
 */
@Data
public abstract class AbstractMultiFactor implements MultiFactor {

    protected String fieldName;
    protected OptionType optionType;
    protected Object value;
    protected Object toValue;

    @Override
    public <T> void handle(QueryWrapper<T> wrapper) {
        if (null == value && null == toValue) {
            return;
        }

        String column = CaseFormatKit.toUpperUnderscore(fieldName);
        switch (optionType) {
            case EQ:
                wrapper.eq(column, value);
                break;
            case GT:
                wrapper.gt(column, value);
                break;
            case GE:
                wrapper.ge(column, value);
                break;
            case LT:
                wrapper.lt(column, value);
                break;
            case LE:
                wrapper.le(column, value);
                break;
            case NE:
                wrapper.ne(column, value);
                break;
            case BETWEEN:
                //  value 等于空, 则小于等于 toValue
                if (null == value) {
                    wrapper.le(column, toValue);
                }
                //  toValue 为空, 则大于等于 value
                if (null == toValue) {
                    wrapper.ge(column, value);
                }
                //  都不空则为 value - toValue 区间
                if (null != value && null != toValue) {
                    wrapper.between(column, value, toValue);
                }
                break;
            case NOT_BETWEEN:
                //  value 等于空, 则大于等于 toValue
                if (null == value) {
                    wrapper.ge(column, toValue);
                }
                //  toValue 为空, 则小于等于 value
                if (null == toValue) {
                    wrapper.le(column, value);
                }
                //  都不空则为 value - toValue 区间
                if (null != value && null != toValue) {
                    wrapper.notBetween(column, value, toValue);
                }
                break;
            default:
        }
    }

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.GT
                || optionType == OptionType.EQ
                || optionType == OptionType.GE
                || optionType == OptionType.LT
                || optionType == OptionType.LE
                || optionType == OptionType.NE
                || optionType == OptionType.BETWEEN
                || optionType == OptionType.NOT_BETWEEN;
    }
}
