package com.tellus.toolkit;

import com.google.common.base.Strings;

public class HideCharKit {

    /**
     * 隐藏内容， 以*代替
     *
     * @param original   原始字符
     * @param beginPlace 开始位置
     * @param length     隐藏长度
     * @return String
     */
    public static String hiddenString(String original, int beginPlace, int length) {
        return hiddenString(original, beginPlace, length, false);
    }

    /**
     * 隐藏内容， 以*代替
     *
     * @param original           原始字符
     * @param beginPlace         开始位置
     * @param length             隐藏长度
     * @param isAdd              超过字符串长度以*补全
     * @param hiddenStringLength 截取固定长度
     * @return String
     */
    public static String hiddenString(String original, int beginPlace, int length, boolean isAdd, int hiddenStringLength) {
        String str = hiddenString(original, beginPlace, length, isAdd);
        if (str.length() > hiddenStringLength) {
            return str.substring(str.length() - hiddenStringLength);
        }
        return str;
    }


    /**
     * 隐藏内容， 以*代替
     *
     * @param original   原始字符
     * @param beginPlace 开始位置
     * @param length     隐藏长度
     * @param isAdd      超过字符串长度以*补全
     * @return String
     */
    public static String hiddenString(String original, int beginPlace, int length, boolean isAdd) {
        if (Strings.isNullOrEmpty(original)) {
            return original;
        }

        int strLength = original.length();
        if (strLength < beginPlace) {
            return original;
        }

        if (length <= 0) {
            length = strLength - beginPlace;
        }

        int residue = original.substring(beginPlace).length();
        String suffix = "";
        if (residue > length) {
            suffix = original.substring(beginPlace + length);
        }
        if (isAdd || !Strings.isNullOrEmpty(suffix)) {
            return original.substring(0, beginPlace) + Strings.repeat("*", length) + suffix;
        }
        return original.substring(0, beginPlace) + Strings.repeat("*", residue) + suffix;
    }
}
