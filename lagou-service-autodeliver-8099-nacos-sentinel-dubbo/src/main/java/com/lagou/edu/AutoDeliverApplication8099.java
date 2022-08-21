package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:49
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AutoDeliverApplication8099 {

    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverApplication8099.class, args);
    }
}
