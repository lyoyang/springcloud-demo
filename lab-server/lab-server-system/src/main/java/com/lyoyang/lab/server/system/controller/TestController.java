package com.lyoyang.lab.server.system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RefreshScope
public class TestController {

    @Value(value = "${account:alice}")
    private String userName;


    @RequestMapping("/test")
    public String test() {
        return "system test";
    }

    @RequestMapping("/userDetail")
    public Principal userDetail(Principal principal) {
        return principal;
    }


    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello " + name;
    }

    @RequestMapping("/getAccount")
    public String getAccount() {
        return userName;
    }




}
