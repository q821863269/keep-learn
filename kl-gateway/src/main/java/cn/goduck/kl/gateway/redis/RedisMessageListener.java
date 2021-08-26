package cn.goduck.kl.gateway.redis;

import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.gateway.cache.UrlPermRolesLocalCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/5/31 10:15
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisMessageListener implements MessageListener {

    private final UrlPermRolesLocalCache urlRolesLocalCache;

    /**
     * 消息监听器
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        String topic = new String(pattern, StandardCharsets.UTF_8);
        log.info("接收到Redis消息：topic：{}，body：{}", topic, body);
        urlRolesLocalCache.remove(RedisConstant.URL_PERM_ROLES_KEY);
    }

}