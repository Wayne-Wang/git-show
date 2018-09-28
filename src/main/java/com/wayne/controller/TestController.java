package com.wayne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Wayne.Wang
 * @Date 18/9/28
 */
@Controller
@RequestMapping("test")
public class TestController {


    @RequestMapping("error")
    public String error() {
        return "errorinfo";
    }

    @RequestMapping("hello-world")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }
}
