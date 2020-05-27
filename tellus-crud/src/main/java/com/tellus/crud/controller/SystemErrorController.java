package com.tellus.crud.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
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
import java.util.Map;
import java.util.UUID;

/**
 * 重写 Spring 默认的错误处理方式
 *
 * @author Roy
 * @date 2020/5/25 22:58
 */
@Slf4j
@ApiIgnore
@RestController
public class SystemErrorController extends BasicErrorController {

    public SystemErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(true), serverProperties.getError());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));

        log.error("Error code: {}, {}", UUID.randomUUID(), new Gson().toJson(body));

        HttpStatus status = getStatus(request);
        String exception = body.get("exception").toString();
        String message = body.get("message").toString();

        Map<String, Object> result = Maps.newConcurrentMap();
        result.put("code", 500);
        result.put("message", message);
        result.put("data", null);
        result.put("successful", false);

        if (!Strings.isNullOrEmpty(exception)) {
            if (exception.equals(TokenExpireException.class.getName())) {
                status = HttpStatus.UNAUTHORIZED;
                result.put("code", HttpStatus.UNAUTHORIZED.value());
                result.put("message", "Token has expired");
            }
        }

        return new ResponseEntity<>(result, status);
    }
}
