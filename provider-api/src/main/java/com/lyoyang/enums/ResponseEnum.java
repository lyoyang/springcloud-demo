package com.lyoyang.enums;

/**
 * 运营-枚举类
 * 
 * @author ipaynow0531
 * @date 2019/6/16/17:50
 */
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * 失败
     */
    FAIL("FAIL", "失败"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

    /**
     * 登录超时
     */
    LOGIN_TIMEOUT("EP001", "登录超时，请重新登录"),

    /**
     * 用户未登录
     */
    NOT_LOGIN_IN("EP002", "用户未登录"),

    /**
     * 未授权
     */
    PERMISSION_DEND("EP003", "未授权访问该接口"),

    /**
     * 未授权
     */
    TOKEN_ERROR("EP004", "Token失效，请重新登录"),

    /**
     * 数据库插入异常
     */
    DB_INSERT_ERROR("EP100", "未授权访问该接口"),

    /**
     * 账号密码错误
     */
    ACCOUNT_PASSWORD_ERROR("APP001", "账号密码错误"),

    /**
     * 用户禁用
     */
    USER_STATUS_FORBID("APP002", "用户状态不可用"),

    /**
     * 图片大小限制
     */
    IMG_SIZE_LIMIT("APP003", "图片过大"),

    /**
     * 验证码过期
     */
    CHECK_CODE_VERDUE("APP004", "验证码过期"),

    /**
     * 两次密码不一致
     */
    PASSWORD_NOT_CONFIRM("APP005", "两次密码不一致");

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
