package cn.goduck.kl.gateway.handler;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.gateway.util.GatewayResponseUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Desc: 网关统一异常处理
 * Author: Kon
 * Date: 2021/6/4 9:52
 */
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    @NonNull
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, @NonNull Throwable throwable) {
        if (serverWebExchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        if (throwable instanceof NotFoundException) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            msg = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
        } else if (throwable instanceof ResponseStatusException) {
            httpStatus = ((ResponseStatusException) throwable).getStatus();
            msg = ((ResponseStatusException) throwable).getMessage();
            log.error("[网关异常处理]请求路径：{} 异常信息：{}", serverWebExchange.getRequest().getPath(), throwable.getMessage());
        }
        return GatewayResponseUtils.write(serverWebExchange, httpStatus, R.failed(msg));
    }

}
