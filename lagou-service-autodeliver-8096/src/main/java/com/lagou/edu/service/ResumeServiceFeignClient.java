package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Jaa
 * @Date 2022/5/24 9:52
 * @Description
 */
// 原来：http://lagou-service-resume/resume/openstate/ + userId
// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务名称）
@FeignClient(value = "lagou-service-resume", fallback = ResumeFallback.class, path = "/resume")
// 使⽤fallback的时候，类上的@RequestMapping的url前缀限定，改成配置在@FeignClient的path属性中
// @RequestMapping("/resume")
public interface ResumeServiceFeignClient {

    // Feign要做的事情，拼装url发起请求
    // 远程调用
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable("userId") Long userId);
}
