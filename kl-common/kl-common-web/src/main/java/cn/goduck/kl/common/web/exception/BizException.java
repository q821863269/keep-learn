package cn.goduck.kl.common.web.exception;

import cn.goduck.kl.common.core.constant.enums.IResultCode;

/**
 * Desc: 业务异常
 * Author: Kon
 * Date: 2021-06-20 12:41
 */
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

}