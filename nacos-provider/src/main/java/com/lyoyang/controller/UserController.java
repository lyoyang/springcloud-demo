package com.lyoyang.controller;

import com.lyoyang.enums.ResponseEnum;
import com.lyoyang.response.MvcResponse;
import com.lyoyang.response.UserInfoResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:31
 * @Description:
 */
@RestController
public class UserController {

    @RequestMapping("/api/getUserInfo")
    MvcResponse<UserInfoResp> getUserInfo() {
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setId(1L);
        userInfoResp.setAge(23);
        userInfoResp.setPhone("13223444323");
        userInfoResp.setEmail("grace@32423.com");
        userInfoResp.setUserName("grace");
        return new MvcResponse(ResponseEnum.SUCCESS, userInfoResp);
    }

    @RequestMapping("/userDetail")
    public String userDetail() {
        return "userDetail";
    }



}
