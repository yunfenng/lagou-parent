package com.lagou.edu.service;

import org.springframework.stereotype.Component;

/**
 * 降级回退逻辑需要定义一个类，实现FeignClient接口，实现接口中的方法
 */
@Component
public class ResumeFallback implements ResumeServiceFeignClient {
    @Override
    public Integer findDefaultResumeState(Long userId) {
        return -6;
    }
}
