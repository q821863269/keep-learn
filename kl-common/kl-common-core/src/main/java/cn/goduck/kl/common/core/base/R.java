package cn.goduck.kl.common.core.base;

import cn.goduck.kl.common.core.constant.enums.IResultCode;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Desc: 响应类
 * Author: Kon
 * Date: 2021/6/17 11:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "响应类")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    // =========================================================成功响应=========================================================
    public static <T> R<T> ok() {
        return restResult(null, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg);
    }
    // =========================================================成功响应=========================================================

    // =========================================================失败响应=========================================================
    public static <T> R<T> failed() {
        return restResult(null, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ResultCode.FAIL.getCode(), msg);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, ResultCode.FAIL.getCode(), msg);
    }

    public static <T> R<T> failed(IResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMsg());
    }
    // =========================================================失败响应=========================================================

    public static <T> R<T> judge(boolean status) {
        if (status) {
            return ok();
        } else {
            return failed();
        }
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    /**
     * 是否成功
     *
     * @param result 响应信息主体
     * @return 是否成功
     */
    public static boolean isOk(R<?> result) {
        return ObjectUtil.isNotNull(result) && ResultCode.SUCCESS.getCode() == result.getCode();
    }

    /**
     * 是否失败
     *
     * @param result 响应信息主体
     * @return 是否失败
     */
    public static boolean isFailed(R<?> result) {
        return !isOk(result);
    }

}