package cn.goduck.kl.gateway.security;

import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.gateway.cache.UrlPermRolesLocalCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/26 11:55
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UrlPermRolesLocalCache urlPermRolesLocalCache;

    // 是否开启本地缓存
    @Value("${security.localCache.enabled:true}")
    private Boolean localCacheEnabled;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 预检请求放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return just(true);
        }

        // Restful接口权限设计 @link https://www.cnblogs.com/haoxianrui/p/14961707.html
        PathMatcher pathMatcher = new AntPathMatcher();
        String method = request.getMethodValue();
        String path = request.getURI().getPath();
        String restfulPath = method + StrConstant.COLON + path;

        // 从header中获取token
        String token = request.getHeaders().getFirst(AuthConstant.AUTHORIZATION_KEY);
        // 如果token以"bearer "为前缀，到这里说明JWT有效即已认证
        if (ObjectUtil.isNotNull(token) && token.startsWith(AuthConstant.AUTHORIZATION_PREFIX)) {
            // 移动端请求认证即可，不需后续鉴权
            if (pathMatcher.match(GlobalConstant.APP_API_PATTERN, path)) {
                return just(true);
            }
        } else {
            return just(true);
        }

        // 缓存取 URL权限-角色集合 规则数据
        // urlPermRolesRules = [{'key':'GET:/api/v1/users/*','value':['ADMIN','TEST']},...]
        Map<String, List<String>> urlRolesMap = getUrlPermRolesMap();

        // 根据请求路径判断有访问权限的角色列表(拥有访问权限的角色)
        List<String> authorizedRoles = null;
        // 是否需要鉴权，默认 没有设置权限规则 不用鉴权
        boolean requireCheck = false;

        // 循环所有URL权限规则，判断是否需要鉴权
        for (Map.Entry<String, List<String>> entry : urlRolesMap.entrySet()) {
            String urlPerm = entry.getKey();
            if (pathMatcher.match(urlPerm, restfulPath)) {
                requireCheck = true;
                // URL权限对应的角色
                authorizedRoles = entry.getValue();
                break;
            }
        }

        // 不需要权限
        if (!requireCheck) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 需要权限，判断JWT中携带的用户角色是否有权限访问
        return checkAuthorization(mono, authorizedRoles);
    }

    private Mono<AuthorizationDecision> just(boolean granted) {
        return Mono.just(new AuthorizationDecision(granted));
    }

    private Map<String, List<String>> getUrlPermRolesMap() {
        Map<String, List<String>> urlRolesMap;
        HashOperations<String, String, List<String>> hashOperations = redisTemplate.opsForHash();
        if (localCacheEnabled) {
            // 先从本地缓存取
            urlRolesMap = urlPermRolesLocalCache.getCache(RedisConstant.URL_PERM_ROLES_KEY);
            if (ObjectUtil.isNull(urlRolesMap)) {
                // 再从redis中获取
                urlRolesMap = hashOperations.entries(RedisConstant.URL_PERM_ROLES_KEY);
                if (ObjectUtil.isNotNull(urlRolesMap)) {
                    // redis中获取到则存入本地缓存
                    urlPermRolesLocalCache.setLocalCache(RedisConstant.URL_PERM_ROLES_KEY, urlRolesMap);
                }
            }
        } else {
            urlRolesMap = hashOperations.entries(RedisConstant.URL_PERM_ROLES_KEY);
        }
        return urlRolesMap;
    }

    private Mono<AuthorizationDecision> checkAuthorization(Mono<Authentication> mono, List<String> authorizedRoles) {
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    // 用户的角色
                    String roleCode = authority.substring(AuthConstant.AUTHORITY_PREFIX.length());
                    if (GlobalConstant.ROOT_ROLE_CODE.equals(roleCode)) {
                        // 如果是超级管理员则放行
                        return true;
                    } else {
                        return CollectionUtil.isNotEmpty(authorizedRoles) && authorizedRoles.contains(roleCode);
                    }
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
