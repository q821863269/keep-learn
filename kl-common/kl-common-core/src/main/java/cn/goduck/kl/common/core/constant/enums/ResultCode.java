package cn.goduck.kl.common.core.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Desc: 响应码枚举
 * Author: Kon
 * Date: 2021/6/17 13:42
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultCode", description = "响应码-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultCode implements IResultCode {

    SUCCESS(0, "ok"),
    FAIL(1, "系统异常"),

    // 基础相关
    EXCEL_IS_EMPTY(101, "Excel没有数据"),

    // 登录相关
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_ACCOUNT_DISABLED(1002, "用户账号被禁用"),
    USER_ACCOUNT_LOCKED(1003, "用户账号被锁定"),
    USER_ACCOUNT_EXPIRED(1004, "用户账号已过期"),
    REQUEST_NOT_FOUND_CLIENT_DETAILS(1010, "请求中未包含客户端信息"),
    USERNAME_OR_PASSWORD_ERROR(1011, "用户名或密码错误"),
    INPUT_PASSWORD_EXCEED_LIMIT(1012, "用户输入密码次数超限"),
    CLIENT_AUTHENTICATION_FAILED(1013, "客户端认证失败"),
    TOKEN_INVALID_OR_EXPIRED(1014, "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN(1015, "token已被禁止访问"),

    // 权限相关
    AUTHORIZED_ERROR(2001, "访问权限异常"),
    ACCESS_UNAUTHORIZED(2002, "访问未授权"),

    // 用户相关
    USERNAME_EXISTS(3001, "用户名已存在")
    ;

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "描述")
    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}