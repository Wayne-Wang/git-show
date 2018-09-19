package com.wayne.controller;

import com.alibaba.fastjson.JSON;
import com.wayne.core.collection.Collections;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Wayne.Wang
 * @Date 18/9/19
 */
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler({Exception.class})
    public String exception(Exception e, HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        logger.error("请求处理异常：", e);
        if (isAjax(request)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(Collections.asMap("RespDesc",
                    StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "系统异常，请稍后再试！")));
            writer.flush();
            writer.close();
            return null;
        }
        return "errorinfo";
    }

    /**
     * 判断是否异步请求.
     *
     * @return true异步.
     */
    protected boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)) {
            return true;
        }
        return false;
    }

}
