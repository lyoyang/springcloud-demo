package com.lyoyang.lab.common.handler;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.enums.ResponseEnum;
import com.lyoyang.lab.common.utils.LabUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LabAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        LabUtil.createResponse(httpServletResponse, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_FORBIDDEN,ServiceResponse.getServiceResponse(ResponseEnum.UNAUTHORIZED));
    }
}
