package cn.goduck.kl.test.boot.controller;

import cn.goduck.kl.common.core.base.R;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/24 15:33
 */
@RestController
@RequestMapping("/redis")
@AllArgsConstructor
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;

    @GetMapping("/get")
    public R<Object> get(String key) {
        return R.ok(redisTemplate.opsForValue().get(key));
    }

    @GetMapping("/set")
    public R<Boolean> set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return R.ok();
    }

    @GetMapping("/redisson")
    public R<List<String>> redisson() {
        Iterable<String> keys = redissonClient.getKeys().getKeys();
        List<String> list = Lists.newArrayList();
        keys.forEach(list::add);
        return R.ok(list);
    }

}