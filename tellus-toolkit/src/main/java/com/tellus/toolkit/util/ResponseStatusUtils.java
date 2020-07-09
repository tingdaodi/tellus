package com.tellus.toolkit.util;

import com.tellus.support.Result;
import com.tellus.support.annotation.Nullable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Response 返回处理
 *
 * @author Roy
 * @date 2020/7/9 13:16
 */
@Slf4j
public class ResponseStatusUtils {

    public static void writer(HttpServletResponse response, Result<?> result) {
        ResponseStatusUtils.writer(response, result);
    }

    @SneakyThrows
    public static void writer(HttpServletResponse response, Result<?> result, @Nullable HttpStatus httpStatus) {
        log.info("{}, {}, {}", httpStatus, response, result);
        if (null != httpStatus) {
            response.setStatus(httpStatus.value());
        }
        if (null == response.getContentType()) {
            response.setContentType("application/json");
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
    }

}
