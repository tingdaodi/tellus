package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.CaseFormatKit;

/**
 * 字符穿条件因子
 *
 * @author Roy
 * @date 2020/5/26 11:46
 */
public class StringFactor extends AbstractFactor {

    @Override
    public <T> void handle(QueryWrapper<T> wrapper, Class<T> cls) {
        if (null == value) {
            return;
        }

        String column = CaseFormatKit.toUpperUnderscore(fieldName);
        switch (optionType) {
            case EQ:
                wrapper.eq(column, value);
                break;
            case NE:
                wrapper.ne(column, value);
                break;
            case LIKE:
                wrapper.like(column, value);
                break;
            case NOT_LIKE:
                wrapper.notLike(column, value);
                break;
            case LIKE_LEFT:
                wrapper.likeLeft(column, value);
                break;
            case LIKE_RIGHT:
                wrapper.likeRight(column, value);
                break;
            default:
        }
    }

    @Override
    public boolean supported(OptionType optionType) {
        return optionType == OptionType.EQ
                || optionType == OptionType.NE
                || optionType == OptionType.LIKE_LEFT
                || optionType == OptionType.LIKE_RIGHT
                || optionType == OptionType.LIKE
                || optionType == OptionType.NOT_LIKE;
    }
}
