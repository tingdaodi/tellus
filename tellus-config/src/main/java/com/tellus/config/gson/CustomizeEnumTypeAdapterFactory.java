package com.tellus.config.gson;

import cn.hutool.core.convert.Convert;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.tellus.support.annotation.ISerializedName;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写 GSON 枚举转换器
 *
 * @author Roy
 * @date 2020/5/21 11:43
 */
public class CustomizeEnumTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return null;
    }

    private static class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

        private final Map<Object, T> nameToConstant = new HashMap<>();
        private final Map<T, Object> constantToName = new HashMap<>();

        @SneakyThrows
        EnumTypeAdapter(Class<T> classOf) {
            for (T constant : classOf.getEnumConstants()) {
                String name = constant.name();
                // @see ISerializeName.alternate
                nameToConstant.put(name, constant);
                ISerializedName annotation = classOf.getField(name).getAnnotation(ISerializedName.class);
                if (null != annotation) {
                    name = annotation.value();
                    for (String s : annotation.alternate()) {
                        nameToConstant.put(s, constant);
                    }
                    constantToName.put(constant, Convert.convert(annotation.getClass(), name));
                } else {
                    constantToName.put(constant, name);
                }
                nameToConstant.put(name, constant);
            }
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            if (null == value) {
                out.value("");
                return;
            }

            Object o = constantToName.get(value);
            if (o instanceof String) {
                out.value((String) o);
            } else if (o instanceof Integer) {
                out.value((Integer) o);
            } else if (o instanceof Boolean) {
                out.value((Boolean) o);
            } else if (o instanceof Double) {
                out.value((Double) o);
            } else if (o instanceof Long) {
                out.value((Long) o);
            } else if (o instanceof Number) {
                out.value((Number) o);
            } else {
                out.value(String.valueOf(o));
            }
        }

        @Override
        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            // TODO in.nextString() 可能为其他类型数据, 暂时未处理
            return nameToConstant.get(in.nextString());
        }
    }

}
