package com.lyoyang.lab.common.handler;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.enums.ResponseEnum;
import com.lyoyang.lab.common.utils.LabUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LabAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        LabUtil.createResponse(httpServletResponse, MediaType.APPLICATION_JSON_VALUE,
                HttpStatus.UNAUTHORIZED.value(), ServiceResponse.getServiceResponse(ResponseEnum.TOKEN_INVALID));
    }
}
