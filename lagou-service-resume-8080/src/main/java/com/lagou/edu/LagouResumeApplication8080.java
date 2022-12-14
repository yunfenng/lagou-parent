package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:20
 * @Description
 */
@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
// @EnableEurekaClient // 开启Eureka Client （Eureka独有）
@EnableDiscoveryClient // 开启注册中心客户端 （通用型，比如注册到Eureka、Nacos等）
                       // 说明：从SpringCloud的Edgware版本开始，不加注解也可以，建议加上
public class LagouResumeApplication8080 {

    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8080.class, args);
    }
}
