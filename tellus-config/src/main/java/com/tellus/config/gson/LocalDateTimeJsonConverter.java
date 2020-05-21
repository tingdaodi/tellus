package com.tellus.config.gson;

import com.google.common.base.Strings;
import com.google.gson.*;
import com.tellus.toolkit.DateFormatConstants;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate: 序列化,反序列化
 *
 * @author Roy
 * @date 2020/5/21 11:33
 */
public class LocalDateTimeJsonConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateFormatConstants.YYYYMMDDHHMMSS);

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (Strings.isNullOrEmpty(json.getAsString())) {
            return null;
        }
        return LocalDateTime.parse(json.getAsString(), DATE_TIME_FORMATTER);
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DATE_TIME_FORMATTER));
    }
}
