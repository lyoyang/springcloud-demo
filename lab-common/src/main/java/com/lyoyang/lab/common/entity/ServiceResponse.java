package com.lyoyang.lab.common.entity;

import com.lyoyang.lab.common.enums.ResponseEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse<T> {


    private String code;
    private String message;
    private T data;

    public ServiceResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ServiceResponse getFailInstance() {
        return ServiceResponse.builder()
                .code(ResponseEnum.FAIL.getCode())
                .message(ResponseEnum.FAIL.getMessage())
                .build();
    }

    public static ServiceResponse getSuccessInstance() {
        return ServiceResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> ServiceResponse<T> getSuccessInstance(T data) {
        ServiceResponse<T> serviceResponse = new ServiceResponse<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
        return serviceResponse;
    }

    public static ServiceResponse getServiceResponse(ResponseEnum responseEnum) {
        ServiceResponse serviceResponse = new ServiceResponse<>(responseEnum.getCode(), responseEnum.getMessage());
        return serviceResponse;
    }

    public static ServiceResponse getExceptionResponse(String message) {
        ServiceResponse serviceResponse = new ServiceResponse<>(ResponseEnum.SYSTEM_ERRO.getCode(), message);
        return serviceResponse;
    }








}
