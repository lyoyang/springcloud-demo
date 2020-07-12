package com.lyoyang.lab.common.handler;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.enums.ResponseEnum;
import com.lyoyang.lab.common.exception.LabAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return ServiceResponse.getServiceResponse(ResponseEnum.SYSTEM_ERRO);
    }

    @ExceptionHandler(value = LabAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse handleFebsAuthException(LabAuthException e) {
        log.error("系统错误", e);
        return ServiceResponse.getExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ServiceResponse handleAccessDeniedException(){
        return ServiceResponse.getServiceResponse(ResponseEnum.UNAUTHORIZED);
    }



}
