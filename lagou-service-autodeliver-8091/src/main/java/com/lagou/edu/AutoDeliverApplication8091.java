package com.lagou.edu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:49
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
// @EnableHystrix // 开启Hystrix熔断
@EnableCircuitBreaker // 开启熔断器功能
// @SpringCloudApplication // 上面三个的综合
public class AutoDeliverApplication8091 {

    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverApplication8091.class, args);
    }

    /**
     * 注入RestTemplate
     *
     * @return
     */
    @Bean
    @LoadBalanced // Ribbon负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 给被监控的微服务中注册一个Servlet，后期通过访问这个Servlet获取该服务的Hystrix监控数据
     * 前提：被监控的微服务需要引入Springboot的actuator功能
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
