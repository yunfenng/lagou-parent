package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
// @EnableEurekaClient // 开启Eureka Client （Eureka独有）
@EnableDiscoveryClient // 开启注册中心客户端 （通用型，比如注册到Eureka、Nacos等）
                       // 说明：从SpringCloud的Edgware版本开始，不加注解也可以，建议加上
public class LagouResumeApplication8083 {

    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8083.class, args);
    }
}
