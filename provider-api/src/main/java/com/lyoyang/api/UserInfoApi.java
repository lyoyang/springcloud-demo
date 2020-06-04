package com.lyoyang.api;

import com.lyoyang.fallback.UserInfoApiFallBack;
import com.lyoyang.response.MvcResponse;
import com.lyoyang.response.UserInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:03
 * @Description:
 */
@FeignClient(value = "nacos-user-provider", fallback = UserInfoApiFallBack.class)
public interface UserInfoApi {

    @RequestMapping(value = "/api/getUserInfo")
    MvcResponse<UserInfoResp> getUserInfo();
}
