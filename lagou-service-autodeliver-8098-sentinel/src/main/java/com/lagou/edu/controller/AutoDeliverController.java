package com.lagou.edu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lagou.edu.config.SentinelHandlersClass;
import com.lagou.edu.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jaa
 * @Date 2022/5/24 9:47
 * @Description
 */
@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping("/checkState/{userId}")
    // @SentinelResource注解类似于Hystrix中的@HystrixCommand注解
    /**
     * @SentinelResource
     *  value：定义资源名
     *  blockHandlerClass：指定Sentinel规则异常兜底逻辑所在class类
     *  blockHandler：指定Sentinel规则异常兜底逻辑具体哪个⽅法
     *  fallbackClass：指定Java运⾏时异常兜底逻辑所在class类
     *  fallback：指定Java运⾏时异常兜底逻辑具体哪个⽅法
     */
    @SentinelResource(value = "findResumeOpenState",
            blockHandlerClass = SentinelHandlersClass.class, blockHandler = "handleException",
            fallbackClass = SentinelHandlersClass.class, fallback = "handleError")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 模拟降级：RT
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 模拟降级：异常比例a
        int i = 1 / 0;
        Integer defaultResumeState = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState;
    }



}
