package cn.goduck.kl.gateway.security;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.gateway.util.GatewayResponseUtils;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/26 14:42
 */
@Slf4j
@Component
@AllArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    private final RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        // 非JWT或者JWT为空不作处理
        String token = request.getHeaders().getFirst(AuthConstant.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !token.startsWith(AuthConstant.AUTHORIZATION_PREFIX)) {
            return chain.filter(exchange);
        }

        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在拦截响应token失效
        token = token.replace(AuthConstant.AUTHORIZATION_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        String jti = jsonObject.getStr(AuthConstant.JWT_JTI);
        Boolean isBlack = redisTemplate.hasKey(RedisConstant.buildKey(RedisConstant.AUTH_TOKEN_BLACKLIST_PREFIX, jti));
        if (BooleanUtil.isTrue(isBlack)) {
            return GatewayResponseUtils.write(exchange, HttpStatus.FORBIDDEN, R.failed(ResultCode.TOKEN_ACCESS_FORBIDDEN));
        }

        // 向request中写入JWT的载体信息
        request = exchange.getRequest().mutate()
                .header(AuthConstant.JWT_PAYLOAD_KEY, URLUtil.encode(payload, CharsetUtil.CHARSET_UTF_8))
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}