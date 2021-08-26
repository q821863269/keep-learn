package cn.goduck.kl.gateway.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/26 11:37
 */
@Slf4j
@Component
public class UrlPermRolesLocalCache {

    private Cache<String, Map<String, String>> localCache = null;

    @PostConstruct
    private void init() {
        localCache = CacheBuilder.newBuilder()
                //设置本地缓存容器的初始容量
                .initialCapacity(1)
                //设置本地缓存的最大容量
                .maximumSize(30)
                //设置写缓存后多少秒过期
                .expireAfterWrite(300, TimeUnit.SECONDS).build();
    }

    public void setLocalCache(String key, Map<String, String> object) {
        localCache.put(key, object);
    }

    public Map<String, String> getCache(String key) {
        return localCache.getIfPresent(key);
    }

    public void remove(String key) {
        localCache.invalidate(key);
    }

}