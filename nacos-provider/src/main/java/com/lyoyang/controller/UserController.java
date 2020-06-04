package com.lyoyang.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lyoyang.enums.ResponseEnum;
import com.lyoyang.response.MvcResponse;
import com.lyoyang.response.UserInfoResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:31
 * @Description:
 */
@RestController
@RefreshScope
public class UserController {

    @Value(value = "${account:alice}")
    private String userName;


    @RequestMapping("/api/getUserInfo")
    MvcResponse<UserInfoResp> getUserInfo() {
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setId(1L);
        userInfoResp.setAge(23);
        userInfoResp.setPhone("13223444323");
        userInfoResp.setEmail("grace@32423.com");
        userInfoResp.setUserName(userName);
        return new MvcResponse(ResponseEnum.SUCCESS, userInfoResp);
    }

    @RequestMapping("/userDetail")
    public String userDetail() {
        return "userDetail";
    }



}
