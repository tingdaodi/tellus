package com.tellus.support.enums.basic;

/**
 * 条件操作符
 *
 * @author Roy
 * @date 2020/5/17 17:04
 */
public enum OptionType {

    /**
     * 等于 =
     */
    EQ,
    /**
     * 不等于 ！=
     */
    NE,
    /**
     * 大于 >
     */
    GT,
    /**
     * 大于等于 >=
     */
    GE,
    /**
     * 小于
     */
    LT,
    /**
     * 小于等于 <=
     */
    LE,
    /**
     * BETWEEN 值1
     */
    BETWEEN,
    /**
     * NOT_BETWEEN 值1
     */
    NOT_BETWEEN,
    /**
     * BETWEEN/NOT_BETWEEN 值1 AND 值2
     */
    AND,
    /**
     * LIKE ‘%value%’
     */
    LIKE,
    /**
     * NOT LIKE ‘%%value’
     */
    NOT_LIKE,
    /**
     * LIKE ‘value%’
     */
    LIKE_LEFT,
    /**
     * LIKE '%value'
     */
    LIKE_RIGHT,
    /**
     * IN
     */
    IN,
    /**
     * NOT IN
     */
    NOT_IN;


}
