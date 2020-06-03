package com.lyoyang.orderservice.controller;

import com.lyoyang.api.MemberApi;
import com.lyoyang.dto.MemberRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private MemberApi memberApi;

    @RequestMapping("/getMembers")
    public List<MemberRespDto> getAllUser() {
        return memberApi.getMembers();
    }

}
