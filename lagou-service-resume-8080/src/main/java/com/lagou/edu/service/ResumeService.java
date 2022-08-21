package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:13
 * @Description
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
