package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

/**
 * @Author Jaa
 * @Date 2022/6/19 15:43
 * @Description
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication9411 {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication9411.class, args);
    }

    /**
     * 注⼊事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
