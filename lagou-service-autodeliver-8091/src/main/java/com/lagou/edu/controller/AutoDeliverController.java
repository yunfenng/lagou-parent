package com.lagou.edu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:49
 * @Description
 */
@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private RestTemplate restTemplate;

    /*// "/autodeliver/checkState/1545132"
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 调用远程服务 --> 简历微服务接口 RestTemplate --> JdbcTemplate
        // httpClient 封装好多内容进行远程调用
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);
        return forObject;
    }*/


    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务注册到Eureka之后的改造
     *
     * @param userId
     * @return
     *//*
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // TODO 从Eureka Server中获取我们关注的那个服务的实例信息和接口信息
        // 1、从Eureka Server中获取lagou-service-resume服务的实例信息(使用客户端对象做这件事)
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
        // 2、如果有多个实例，选择其中一个（负载均衡的过程）
        ServiceInstance serviceInstance = instances.get(0);
        // 3、从元数据信息中获取host port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        System.out.println("=========>从Eureka Server集群获取的服务实例拼接的url：" + url);

        // 调用远程服务 --> 简历微服务接口 RestTemplate --> JdbcTemplate
        // httpClient 封装好多内容进行远程调用
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }*/

    /**
     * 使用Ribbon负载均衡
     *
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 使用Ribbon不需要我们自己获取服务实例然后选择一个那么访问（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制
     *
     * @param userId
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "findResumeOpenStateTimeout",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),       // 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")   // 最大等待队列长度
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {
        // 使用Ribbon不需要我们自己获取服务实例然后选择一个那么访问（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /**
     * 1) 服务提供者处理超时，熔断，返回错误信息
     * 2) 有可能服务提供者出现异常直接抛出异常信息
     * <p>
     * 以上信息，都会返回到消费者这⾥，很多时候消费者服务不希望把收到异常错误信息再抛到它的上游去
     * ⽤户微服务 — 注册微服务 — 优惠券微服务
     * 1 登记注册
     * 2 分发优惠券（并不是核⼼步骤），这⾥如果调⽤优惠券微服务返回了异常信息或者是熔断后的错误信息，这些信息如果抛给⽤户很不友好
     * 此时，我们可以返回⼀个兜底数据，预设的默认值（服务降级）
     */
    @HystrixCommand(
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),       // 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")   // 最大等待队列长度
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")

                    // Hystrix高级配置，定制工作过程细节
                    ,
                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 自我修复时活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")
            },
            fallbackMethod = "myFallback" // 回退方法
    )
    @GetMapping("/checkStateTimeoutFallback/{userId}")
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId) {
        // 使用Ribbon不需要我们自己获取服务实例然后选择一个那么访问（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /**
     * 定义回退⽅法，返回预设默认值
     * 注意：该⽅法形参和返回值与原始⽅法保持⼀致
     */
    public Integer myFallback(Long userId) {
        return -1;
    }
}
