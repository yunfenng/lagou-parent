package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Jaa
 * @Date 2022/5/9 0:02
 * @Description
 */
@SpringBootApplication
@EnableEurekaServer // 声明当前项目为Eureka服务
public class LagouEurekaServerApp8762 {

    public static void main(String[] args) {
        SpringApplication.run(LagouEurekaServerApp8762.class, args);
    }
}
