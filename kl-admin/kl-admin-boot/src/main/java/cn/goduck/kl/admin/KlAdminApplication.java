package cn.goduck.kl.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 16:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class KlAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KlAdminApplication.class, args);
    }

}