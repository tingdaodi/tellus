package com.tellus.config.spring;

import com.tellus.support.annotation.ISerializedName;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 重写 spring mvc 枚举转换类, 支持枚举常量转换成枚举
 *
 * @author Roy
 * @date 2020/5/21 13:29
 */
@SuppressWarnings({"rawtypes"})
public class CustomizeStringToEnumConvertFactory implements ConverterFactory<String, Enum> {

    private static Class<?> getEnumType(Class<?> targetType) {
        Assert.notNull(targetType, () -> "The target type " + targetType.getName() + " does not refer to an enum");

        Class<?> enumType = targetType;
        while (null != enumType && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }

        return enumType;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends Enum> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        return new StringToEnum(getEnumType(targetType));
    }

    private static class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Map<String, T> nameToConstants = new HashMap<>();

        @SneakyThrows
        StringToEnum(Class<T> enumType) {
            for (T constant : enumType.getEnumConstants()) {
                String name = constant.name();
                // @see ISerializedName.alternate
                nameToConstants.put(name, constant);

                ISerializedName annotation = enumType.getField(name).getAnnotation(ISerializedName.class);
                if (null != annotation) {
                    name = annotation.value();
                    for (String s : annotation.alternate()) {
                        nameToConstants.put(s, constant);
                    }
                }
                nameToConstants.put(name, constant);
            }
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                //  It's an empty enum identifier: reset the enum value to null
                return null;
            }
            return nameToConstants.get(source);
        }
    }

}
