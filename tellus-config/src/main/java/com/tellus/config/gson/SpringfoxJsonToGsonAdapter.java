package com.tellus.config.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;

/**
 * 自定义转换规则, 定制 springfox 下的 JSON 输出规则
 * <p>
 * 解决 Swagger 与 Gson 不兼容的问题
 *
 * @author Roy
 * @date 2020/5/21 11:37
 */
public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {
    @Override
    public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
        return JsonParser.parseString(src.value());
    }
}
