package com.tellus.permission.cloud.support.gson;

import com.google.common.base.Strings;
import com.google.gson.*;
import com.tellus.permission.cloud.support.FieldCacheUtils;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.support.enums.DisplayModeEnum;
import com.tellus.support.enums.ParamMethodEnum;
import com.tellus.toolkit.HideCharKit;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Gson 重写值序列化
 *
 * @author Roy
 * @date 2020/5/21 11:37
 */
@Slf4j
public class ValuesJsonSerializer<T> implements JsonSerializer<T> {

    private final Gson gson;

    public ValuesJsonSerializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        Set<CustomizeUserDetails.Field> fields = transformFields();
        JsonElement jsonElement = gson.toJsonTree(src);
        if (fields.isEmpty()) {
            return jsonElement;
        }

        if (jsonElement instanceof JsonObject) {
            Set<String> keys = ((JsonObject) jsonElement).keySet();
            for (String key : keys) {
                Optional<CustomizeUserDetails.Field> fieldOptional =
                        fields.stream()
                                .filter(field -> Objects.equals(key, field.getName()))
                                .findAny();
                fieldOptional.ifPresent(field -> {
                    String value = ((JsonObject) jsonElement).get(key).getAsString();
                    ((JsonObject) jsonElement).addProperty(key, stringTransform(value, field.getDisplayMode()));
                });
            }
        }

        return jsonElement;
    }

    private String stringTransform(String name, DisplayModeEnum displayMode) {
        switch (displayMode) {
            case HIDDEN:
                return Strings.repeat("*", name.length());
            case PARTIAL:
                if (name.length() > 2) {
                    return HideCharKit.hiddenString(name, 2, name.length() - 4);
                } else {
                    return HideCharKit.hiddenString(name, 1, name.length());
                }
            case NORMAL:
            default:
                return name;
        }
    }

    private Set<CustomizeUserDetails.Field> transformFields() {
        return FieldCacheUtils.getFields()
                .stream()
                .filter(field -> field.getMethod() == ParamMethodEnum.RETURN)
                .filter(field -> field.getDisplayMode() != DisplayModeEnum.NORMAL)
                .collect(Collectors.toSet());
    }
}
