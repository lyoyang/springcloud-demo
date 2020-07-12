package com.lyoyang.lab.server.test.service;

import com.lyoyang.lab.common.constant.LabServerConstant;
import com.lyoyang.lab.server.test.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = LabServerConstant.LAB_SERVER_SYSTEM)
public interface IHelloService {

    @GetMapping("/hello")
    String hello(String name);

}
