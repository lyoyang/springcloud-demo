package com.lyoyang.controller;

import com.lyoyang.api.UserInfoApi;
import com.lyoyang.response.MvcResponse;
import com.lyoyang.response.UserInfoResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: yangbing
 * @Date: 2019/7/22 16:33
 * @Description:
 */
@RestController
@RequestMapping("/orderController")
public class OrderController {

    @Resource
    private UserInfoApi userInfoApi;

    @RequestMapping("/getUserInfo")
    public MvcResponse<UserInfoResp> getUserInfo() {
        return userInfoApi.getUserInfo();
    }

    @RequestMapping("/orderDetail")
    public String orderDetail() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostName();
    }

}
