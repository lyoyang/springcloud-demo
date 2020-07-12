package com.lyoyang.lab.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lyoyang.lab.common.constant.LabServerConstant;
import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.utils.LabUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LabServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(LabServerConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(LabServerConstant.ZUUL_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            ServiceResponse serviceResponse = ServiceResponse.getExceptionResponse("请通过网关获取资源");
            LabUtil.createResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_FORBIDDEN, serviceResponse);
            return false;
        }
    }
}
