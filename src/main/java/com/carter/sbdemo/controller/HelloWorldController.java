package com.carter.sbdemo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/err")
    public void error() throws Exception {
        throw new Exception("错误请求url");
    }


}
