package cn.goduck.kl.auth.security.exception;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/9 14:20
 */
@Slf4j
@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.error("认证异常，异常原因：{}", e.getMessage(), e);
        Throwable cause = e.getCause();
        if (cause instanceof BizException) {
            BizException exception = (BizException) cause;
            return R.failed(exception.resultCode);
        }
        return R.failed(e.getMessage());
    }

}
