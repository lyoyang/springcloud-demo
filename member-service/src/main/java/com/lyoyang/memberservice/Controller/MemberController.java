package com.lyoyang.memberservice.Controller;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemberController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getAllmember")
    public List<String> getAllMember() {
        ArrayList<String> list = Lists.newArrayList();
        list.add("jim");
        list.add("bob");
        list.add("alice");
        list.add("banana");
        list.add("端口号：" + serverPort);
        return list;
    }

}
