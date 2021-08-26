package cn.goduck.kl.common.redis.config;

import cn.goduck.kl.common.core.constant.RedisConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/25 10:12
 */
@Configuration
@AutoConfigureBefore(RedissonAutoConfiguration.class)
public class RedissonConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        if (ObjectUtil.isNull(redisProperties.getCluster())) {
            // 单机模式
            SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(
                    RedisConstant.REDIS_AGREEMENT + redisProperties.getHost() + StrConstant.COLON + redisProperties.getPort());
            if (StrUtil.isNotBlank(redisProperties.getPassword())) {
                singleServerConfig.setPassword(redisProperties.getPassword());
            }
        } else {
            // 集群模式
            List<String> clusterNodes = new ArrayList<>();
            for (String node : redisProperties.getCluster().getNodes()) {
                clusterNodes.add(RedisConstant.REDIS_AGREEMENT + node);
            }
            ClusterServersConfig clusterServersConfig = config.useClusterServers()
                    .addNodeAddress(clusterNodes.toArray(new String[0]));
            if (StrUtil.isNotBlank(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }
        }
        return Redisson.create(config);
    }

}