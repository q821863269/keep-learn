package cn.goduck.kl.gateway.security;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.gateway.util.GatewayResponseUtils;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/26 11:52
 */
@ConfigurationProperties(prefix = "security")
@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final ResourceServerManager resourceServerManager;

    @Setter
    private List<String> ignoreUrls;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
                // ??????????????????
                .publicKey(rsaPublicKey())
                // ??????????????????
                //.jwkSetUri()
        ;
        http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
        http.authorizeExchange()
                .pathMatchers(Convert.toStrArray(ignoreUrls)).permitAll()
                .anyExchange().access(resourceServerManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler()) // ???????????????
                .authenticationEntryPoint(authenticationEntryPoint()) //???????????????
                .and().csrf().disable();

        return http.build();
    }

    /**
     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
     * <p>
     * ServerHttpSecurity?????????jwt???authorities?????????????????????Authentication
     * ?????????jwt???Claim??????authorities??????
     * ??????????????????????????????????????????????????????JwtGrantedAuthoritiesConverter
     */
    protected Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.JWT_AUTHORITIES_KEY);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    /**
     * ????????????JWT????????????
     */
    @SneakyThrows
    protected RSAPublicKey rsaPublicKey() {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String publicKeyData = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(publicKeyData)));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * ?????????token???????????????????????????
     */
    protected ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> GatewayResponseUtils.write(
                        response, HttpStatus.UNAUTHORIZED, R.failed(ResultCode.TOKEN_INVALID_OR_EXPIRED)));
    }

    /**
     * ????????????????????????
     */
    protected ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> GatewayResponseUtils.write(
                        response, HttpStatus.UNAUTHORIZED, R.failed(ResultCode.ACCESS_UNAUTHORIZED)));
    }

}