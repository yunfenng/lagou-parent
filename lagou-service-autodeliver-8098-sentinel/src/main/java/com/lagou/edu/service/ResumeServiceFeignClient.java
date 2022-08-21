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
@FeignClient(value = "lagou-service-resume", path = "/resume")
public interface ResumeServiceFeignClient {

    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable("userId") Long userId);
}
