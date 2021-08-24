package cn.goduck.kl.test.boot.controller;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.test.boot.domain.Test;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @GetMapping("/setObject")
    public R<Boolean> setObject(String key, String name) {
        Test test = new Test();
        test.setId(RandomUtil.randomLong());
        test.setName(name);
        test.setCreateBy(RandomUtil.randomLong());
        test.setCreateTime(LocalDateTime.now());
        test.setUpdateBy(RandomUtil.randomLong());
        test.setUpdateTime(LocalDateTime.now());
        redisTemplate.opsForValue().set(key, test);
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