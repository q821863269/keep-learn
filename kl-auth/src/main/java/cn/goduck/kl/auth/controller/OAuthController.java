package cn.goduck.kl.auth.controller;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.util.JwtUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/25 16:47
 */
@Api(tags = "认证接口")
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
public class OAuthController {

    private final TokenEndpoint tokenEndpoint;
    private final KeyPair keyPair;
    private final RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    @ApiOperation(value = "OAuth2认证", notes = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID（新版本需放置请求头）", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥（新版本需放置请求头）", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码")
    })
    @PostMapping("/token")
    public Object postAccessToken(@ApiIgnore Principal principal, @ApiIgnore @RequestParam Map<String, String> parameters) {
        String clientId = JwtUtil.getOAuthClientId();
        OAuth2AccessToken oauth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        if ("client".equals(clientId)) {
            // knife4j接口测试文档使用 client_id/client_secret : client/123456
            return oauth2AccessToken;
        } else {
            return R.ok(oauth2AccessToken);
        }
    }

    @ApiOperation(value = "注销", notes = "logout")
    @DeleteMapping("/logout")
    public R<String> logout() {
        JSONObject payload = JwtUtil.getJwtPayload();
        // JWT唯一标识
        String jti = payload.getStr(AuthConstant.JWT_JTI);
        // JWT过期时间戳(单位：秒)
        Long expireTime = payload.getLong(AuthConstant.JWT_EXP);
        // redis key
        String key = RedisConstant.buildKey(RedisConstant.AUTH_TOKEN_BLACKLIST_PREFIX, jti);
        if (ObjectUtil.isNotNull(expireTime)) {
            // 当前时间（单位：秒）
            long currentTime = System.currentTimeMillis() / 1000;
            // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
            if (expireTime > currentTime) {
                redisTemplate.opsForValue().set(key, StrConstant.EMPTY, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else {
            // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(key, StrConstant.EMPTY);
        }
        return R.ok("注销成功");
    }

    @ApiOperation(value = "获取公钥", notes = "login")
    @GetMapping("/publicKey")
    public R<Map<String, Object>> publicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        Map<String, Object> result = new JWKSet(key).toJSONObject();
        return R.ok(result);
    }

}