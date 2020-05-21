package com.tellus.config.gson;

import com.google.common.base.Strings;
import com.google.gson.*;
import com.tellus.toolkit.DateFormatConstants;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate: 序列化,反序列化
 *
 * @author Roy
 * @date 2020/5/21 11:33
 */
public class LocalDateJsonConverter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateFormatConstants.YYYYMMDD);

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (Strings.isNullOrEmpty(json.getAsString())) {
            return null;
        }
        return LocalDate.parse(json.getAsString(), DATE_TIME_FORMATTER);
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DATE_TIME_FORMATTER));
    }
}
