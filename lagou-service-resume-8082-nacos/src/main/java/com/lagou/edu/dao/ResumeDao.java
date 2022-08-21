package com.lagou.edu.dao;

import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Jaa
 * @Date 2022/5/8 16:12
 * @Description
 */
public interface ResumeDao extends JpaRepository<Resume, Long> {
}
