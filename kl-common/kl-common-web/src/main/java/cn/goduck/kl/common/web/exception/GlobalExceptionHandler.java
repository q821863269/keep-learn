package cn.goduck.kl.common.web.exception;

import cn.goduck.kl.common.core.base.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-20 12:39
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public R<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public R<Object> handleJsonProcessingException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public R<Object> handleBizException(BizException e) {
        log.error("业务异常，异常原因：{}", e.getMessage(), e);
        return R.failed(e.resultCode);
    }

    @ExceptionHandler(Exception.class)
    public R<Object> handleException(Exception e) {
        log.error("未知异常，异常原因：{}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

}