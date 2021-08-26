package cn.goduck.kl.gateway.redis;

import cn.goduck.kl.common.core.constant.RedisConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/26 11:29
 */
@Configuration
public class RedisChannelConfig {

    /**
     * 定义redis消息监听容器
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       RedisMessageListener redisMessageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(redisMessageListener, new ChannelTopic(RedisConstant.CLEAN_ROLE_LOCAL_CACHE));
        return container;
    }

}
