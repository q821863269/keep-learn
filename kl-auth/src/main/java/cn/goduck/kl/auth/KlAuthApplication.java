package cn.goduck.kl.auth;

import cn.goduck.kl.admin.api.OAuthClientFeignClient;
import cn.goduck.kl.admin.api.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 15:54
 */
@EnableFeignClients(basePackageClasses = {OAuthClientFeignClient.class, UserFeignClient.class})
@EnableDiscoveryClient
@SpringBootApplication
public class KlAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(KlAuthApplication.class, args);
    }

}