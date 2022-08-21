package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/5/25 10:22
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication9002.class, args);
    }
}
