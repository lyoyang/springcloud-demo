package com.lyoyang.lab.gateway.controller;

import com.lyoyang.lab.common.entity.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @RequestMapping(name = "fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ServiceResponse> systemFallback(@PathVariable String name) {
        String msg = String.format("访问%s超时或者服务不可用", name);
        return Mono.just(ServiceResponse.getExceptionResponse(msg));
    }

}
