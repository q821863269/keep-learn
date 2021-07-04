package cn.goduck.kl.gateway.config;

import cn.goduck.kl.gateway.handler.GatewayExceptionHandler;
import cn.goduck.kl.gateway.handler.SentinelExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Desc: 网关配置
 * Author: Kon
 * Date: 2021-06-03 23:08
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置Sentinel限流后异常处理
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelExceptionHandler sentinelExceptionHandler() {
        return new SentinelExceptionHandler();
    }

    @Bean
    @Order(-1)
    public ErrorWebExceptionHandler gatewayExceptionHandler() {
        return new GatewayExceptionHandler();
    }

}