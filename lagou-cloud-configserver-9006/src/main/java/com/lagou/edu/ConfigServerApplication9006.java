package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author Jaa
 * @Date 2022/5/26 23:38
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer // 开始配置服务
public class ConfigServerApplication9006 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication9006.class, args);
    }
}
