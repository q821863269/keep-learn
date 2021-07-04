package cn.goduck.kl.gateway.handler;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.gateway.util.GatewayResponseUtils;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * Desc: Sentinel 限流后自定义异常
 * Author: Kon
 * Date: 2021-06-03 23:24
 */
@Slf4j
public class SentinelExceptionHandler implements WebExceptionHandler {

    @NonNull
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, @NonNull Throwable throwable) {
        if (serverWebExchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }
        if (!BlockException.isBlockException(throwable)) {
            return Mono.error(throwable);
        }
        Mono<ServerResponse> serverResponseMono = GatewayCallbackManager.getBlockHandler().handleRequest(serverWebExchange, throwable);
        R<Object> result = R.failed(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
        log.error("[网关限流异常处理]请求路径：{}", serverWebExchange.getRequest().getPath());
        return serverResponseMono.flatMap(response -> GatewayResponseUtils.write(serverWebExchange, HttpStatus.TOO_MANY_REQUESTS, result));
    }

}
