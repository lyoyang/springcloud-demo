package com.lyoyang.lab.server.test.fallback;

import com.lyoyang.lab.server.test.service.IHelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {


    @Override
    public IHelloService create(Throwable throwable) {
        return new IHelloService() {
            @Override
            public String hello(String name) {
                return "调用出错";
            }
        };
    }
}
