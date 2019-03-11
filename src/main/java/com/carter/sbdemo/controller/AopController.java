package com.carter.sbdemo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {


    @RequestMapping("/before")
    public String beforeService(String key ,String value){
        return "key : "+key+ ", value : "+value;
    }

    @RequestMapping("/after")
    public String afterService(String key){
        return "key = "+key;
    }

    @RequestMapping("/exception")
    public String afterThrownService(String key){
        throw new NullPointerException();
    }

    @RequestMapping("/finally1")
    public String testAfter1(String key){
        throw new NullPointerException();
    }

    @RequestMapping("/finally2")
    public String testAfter2(String key){
        return key;
    }


    @RequestMapping("/arround")
    public String arround(String key){
        return key;
    }

}
