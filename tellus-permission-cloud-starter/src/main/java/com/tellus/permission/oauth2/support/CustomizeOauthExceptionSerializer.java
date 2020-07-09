package com.tellus.permission.oauth2.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tellus.support.Result;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义: OAuth2 异常序列化
 *
 * @author Roy
 * @date 2020/7/9 11:38
 */
public class CustomizeOauthExceptionSerializer extends StdSerializer<CustomizeOauthException> {

    private static final long serialVersionUID = -2605537915364103539L;

    public CustomizeOauthExceptionSerializer() {
        super(CustomizeOauthException.class);
    }

    @Override
    public void serialize(CustomizeOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String errorMessage = value.getMessage();
        if (null != errorMessage) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }

        Map<String, String> info = value.getAdditionalInformation();
        info.put("error_description", errorMessage);
        gen.writeObject(Result.error(value.getHttpErrorCode(), value.getOAuth2ErrorCode(), info));
    }
}
