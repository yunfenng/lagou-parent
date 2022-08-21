package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Author Jaa
 * @Date 2022/5/23 21:49
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine // 开启聚合功能
public class HystrixTurbineApplication9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication9001.class, args);
    }
}
