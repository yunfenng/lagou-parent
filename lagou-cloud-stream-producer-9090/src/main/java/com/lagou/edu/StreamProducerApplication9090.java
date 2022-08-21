package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/6/4 17:15
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamProducerApplication9090 {

    public static void main(String[] args) {
        SpringApplication.run(StreamProducerApplication9090.class, args);
    }

}
