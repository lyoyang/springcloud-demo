package com.lyoyang.orderservicefeign.Controller;

import com.lyoyang.orderservicefeign.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/getAllUser")
    public List<String> getAllUser() {
        return memberService.getAllUser();
    }
}
