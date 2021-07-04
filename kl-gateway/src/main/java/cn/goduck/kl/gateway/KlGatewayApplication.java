package cn.goduck.kl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/17 16:07
 */
@SpringBootApplication
public class KlGatewayApplication {

    public static void main(String[] args) {
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(KlGatewayApplication.class, args);
    }

}