package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/6/4 22:43
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamConsumerApplication9091 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication9091.class, args);
    }
}
