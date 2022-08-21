package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author Jaa
 * @Date 2022/5/20 8:09
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard // 开启Hystrix dashboard
public class HystrixDashboardApplication9000 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication9000.class, args);
    }
}
