package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟共享的配置信息
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    // 和取本地配置信息一样
    @Value("${lagou.message}")
    private String lagouMessage;
    @Value("${mysql.url}")
    private String mysqlUrl;

    @Value("${abc.test}")
    private String abcTest;
    @Value("${def.test}")
    private String defTest;

    @Value("${java.first}")
    private String javaFirst;


    // 内存级别的配置信息
    // 数据库，redis配置信息

    @GetMapping("/viewconfig")
    public String viewConfig() {
        return "lagouMessage==>" + lagouMessage  + " mysqlUrl=>" + mysqlUrl
                + " abcTest=>" + abcTest + " defTest=>" + defTest
                + " javaFirst=>" + javaFirst;
    }
}