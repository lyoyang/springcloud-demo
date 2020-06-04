package com.lyoyang.fallback;

import com.lyoyang.api.UserInfoApi;
import com.lyoyang.enums.ResponseEnum;
import com.lyoyang.response.MvcResponse;
import com.lyoyang.response.UserInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:08
 * @Description:
 */
@Slf4j
@Service
public class UserInfoApiFallBack implements UserInfoApi {

    @Override
    public MvcResponse<UserInfoResp> getUserInfo() {
        return new MvcResponse<>(ResponseEnum.FAIL);
    }
}
