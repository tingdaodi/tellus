package com.tellus.config.gson;

import com.google.gson.GsonBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.lang.NonNull;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * 重写 GSON 消息转换器
 *
 * @author Roy
 * @date 2020/5/21 12:02
 */
public class CustomizeGsonHttpMessageConverter extends GsonHttpMessageConverter {

    private Supplier<GsonBuilder> gsonBuilderSupplier;
    private GsonBuilder gsonBuilder;

    public CustomizeGsonHttpMessageConverter(Supplier<GsonBuilder> gsonBuilderSupplier) {
        this.gsonBuilderSupplier = gsonBuilderSupplier;
        this.setGson(gsonBuilderSupplier.get().create());
    }

    public CustomizeGsonHttpMessageConverter(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
        this.setGson(gsonBuilder.create());
    }

    @NonNull
    @Override
    protected Object readInternal(@NonNull Type resolvedType, @NonNull Reader reader) throws Exception {
        GsonBuilder gsonBuilder;
        if (null != gsonBuilderSupplier) {
            gsonBuilder = gsonBuilderSupplier.get();
        } else {
            gsonBuilder = this.gsonBuilder;
        }
        return gsonBuilder.create().fromJson(reader, resolvedType);
    }

    @Override
    protected void writeInternal(@NonNull Object o, Type type, @NonNull Writer writer) throws Exception {
        GsonBuilder gsonBuilder;
        if (null != gsonBuilderSupplier) {
            gsonBuilder = gsonBuilderSupplier.get();
        } else {
            gsonBuilder = this.gsonBuilder;
        }

        if (type instanceof ParameterizedType) {
            gsonBuilder.create().toJson(o, type, writer);
        } else {
            gsonBuilder.create().toJson(o, writer);
        }
    }
}
