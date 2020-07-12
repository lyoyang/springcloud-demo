package com.lyoyang.lab.server.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {


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




}
