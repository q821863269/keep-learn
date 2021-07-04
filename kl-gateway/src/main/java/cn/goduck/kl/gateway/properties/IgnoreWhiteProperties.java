package cn.goduck.kl.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 放行白名单配置
 * Author: Kon
 * Date: 2021/6/3 16:59
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {

    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    private List<String> whites = new ArrayList<>();

}