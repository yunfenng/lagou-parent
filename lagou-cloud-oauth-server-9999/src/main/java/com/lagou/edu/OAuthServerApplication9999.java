package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Jaa
 * @Date 2022/7/3 17:42
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.lagou.edu.pojo")
public class OAuthServerApplication9999 {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApplication9999.class, args);
    }
}
