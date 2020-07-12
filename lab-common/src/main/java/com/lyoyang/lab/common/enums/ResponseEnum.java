package com.lyoyang.lab.common.enums;

/**
 * @author: yangbing
 * @Date: 2020/4/27 15:19
 * @Description:
 */
public enum ResponseEnum {

    SYSTEM_ERROR("B0001", "系统繁忙"),
    /** 操作失败 */
    OPERATION_ERRO("B0001", "操作失败"),
    /** 登录验证码错误 */
    LOGIN_VERIFICATION_CODE_ERRO("B0001", "验证码错误"),
    /** 登录失效 */
    LOGIN_LOSE("B0001", "请重新登录"),

    VERIFY_CODE_ERROR("B0001", "验证码错误"),

    TOKEN_INVALID("B0001", "token失效，请重新登录"),

    SYSTEM_ERRO("B0001", "系統异常"),
    CONFIRM_PASSWD_ERROR("B0001", "两次密码输入不一致"),

    LACK_OF_PARAM("B0001", "缺少请求参数"),

    SUCCESS("00000", "SUCCESS"),
    FAIL("B0001", "FAIL"),
    USER_EXISTS("A0111", "用户已存在"),
    SER_NOT_EXISTS("A0112", "用户不存在"),
    TOKEN_TIME_OUNT("A0113", "toke过期请重新获取"),

    UNAUTHORIZED("A0301", "访问未授权"),
    LOGIN_TIMEOUT("A0230", "请重新登录"),

    ROLE_ID_EXISTS("R0001", "角色ID已存在"),

    INVALID_REFRESH_TOKEN("R0001", "refresh token 无效"),

    USER_LOKED("R0001", "用户被锁定，请联系管理员"),

    USERNAME_PASSWORD_ERROR("R0001", "用户名或密码错误"),

    AUTHENTICATION_FAILURE("R0001", "认证失败"),

    UNSUPPORTED_GRANT_TYPE("R0001", "不支持认证类型");


    private String code;

    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
