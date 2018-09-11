package com.lyoyang.orderservicefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("service-member")
public interface MemberService {

    @RequestMapping("/getAllmember")
    public List<String> getAllUser();
}
