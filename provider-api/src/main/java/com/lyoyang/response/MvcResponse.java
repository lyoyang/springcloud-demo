package com.lyoyang.response;

import com.lyoyang.enums.ResponseEnum;
import lombok.Data;

import java.util.List;

/**
 * 返回码与信息统一定义在OperationExceptionEnum中
 * @author ipaynow0612
 * @date 2019/6/19 0019
 * @param <T>
 */
@Data
public class MvcResponse<T> {

    public static final String CODE_SUCCESS = "SUCCESS";
    public static final String CODE_FAIL = "FAILURE";
    public static final String CODE_ERROR = "ERROE";

    private String code;

    private String msg;

    private T data;

    private List<T> listResult;

    private Paginator paginator;

    public MvcResponse() {
    }

    public MvcResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MvcResponse(ResponseEnum operationExceptionEnum) {
        this.code  = operationExceptionEnum.getCode();
        this.msg = operationExceptionEnum.getMsg();
    }

    public MvcResponse(ResponseEnum operationExceptionEnum, T data) {
        this.code  = operationExceptionEnum.getCode();
        this.msg = operationExceptionEnum.getMsg();
        this.data = data;
    }

    public MvcResponse(ResponseEnum operationExceptionEnum, List<T> listResult, Paginator paginator) {
        this.code  = operationExceptionEnum.getCode();
        this.msg = operationExceptionEnum.getMsg();
        this.listResult = listResult;
        this.paginator = paginator;
    }
}
