package cn.goduck.kl.gateway.util;

import cn.goduck.kl.common.core.base.R;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Desc: 网关响应工具类
 * Author: Kon
 * Date: 2021/6/11 8:17
 */
public class GatewayResponseUtils {

    public static ServerHttpResponse buildServerHttpResponse(ServerWebExchange serverWebExchange, HttpStatus httpStatus) {
        ServerHttpResponse serverHttpResponse = serverWebExchange.getResponse();
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        serverHttpResponse.setStatusCode(httpStatus);
        return serverHttpResponse;
    }

    public static DataBuffer buildDataBuffer(ServerHttpResponse serverHttpResponse, R<Object> r) {
        byte[] result = JSONObject.toJSONString(r).getBytes(CharsetUtil.CHARSET_UTF_8);
        return serverHttpResponse.bufferFactory().wrap(result);
    }

    public static Mono<Void> write(ServerWebExchange serverWebExchange, HttpStatus httpStatus, R<Object> r) {
        ServerHttpResponse serverHttpResponse = buildServerHttpResponse(serverWebExchange, httpStatus);
        DataBuffer dataBuffer = buildDataBuffer(serverHttpResponse, r);
        return serverHttpResponse.writeWith(Mono.just(dataBuffer));
    }

    public static Mono<Void> write(ServerHttpResponse serverHttpResponse, HttpStatus httpStatus, R<Object> r) {
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        serverHttpResponse.setStatusCode(httpStatus);
        DataBuffer dataBuffer = buildDataBuffer(serverHttpResponse, r);
        return serverHttpResponse.writeWith(Mono.just(dataBuffer));
    }

}
