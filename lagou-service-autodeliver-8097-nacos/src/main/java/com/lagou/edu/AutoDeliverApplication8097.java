package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:49
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // 开启 Feign 客户端功能
public class AutoDeliverApplication8097 {

    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverApplication8097.class, args);
    }
}
