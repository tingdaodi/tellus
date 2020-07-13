package com.tellus.permission.cloud.controller;

import cn.hutool.core.lang.UUID;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tellus.support.exception.TokenExpireException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;
import java.util.Map;

/**
 * description TODO
 *
 * @author Roy
 * @date 2020/7/13 16:08
 */
@Slf4j
@ApiIgnore
@RestController
public class SystemsErrorController extends BasicErrorController {

    // ~ Constructors
    // ==============================================================================

    public SystemsErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(true), serverProperties.getError());
    }

    // ~ Override Methods
    // ==============================================================================

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        String exception = (String) body.get("exception");
        String message = body.get("message").toString();

        log.error("Error Code: " + UUID.fastUUID() + ", " + message + "/n"
                + " [" + body.toString() + "]");

        Map<String, Object> result = Maps.newHashMap();
        result.put("code", "500");
        result.put("message", message);
        result.put("data", "");
        result.put("successful", false);

        if (!Strings.isNullOrEmpty(exception)) {
            if (exception.equals(TokenExpireException.class.getName())
                    || exception.equals(SignatureException.class.getName())) {
                status = HttpStatus.UNAUTHORIZED;
                result.put("code", HttpStatus.UNAUTHORIZED.value());
                result.put("message", "Token已失效");
            }
        }

        return new ResponseEntity<>(result, status);
    }
}
