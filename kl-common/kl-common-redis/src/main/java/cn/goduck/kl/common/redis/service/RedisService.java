package cn.goduck.kl.common.redis.service;

import lombok.Getter;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 15:47
 */
@Service
public class RedisService {

    @Getter
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Getter
    @Resource
    private RedissonClient redissonClient;

}