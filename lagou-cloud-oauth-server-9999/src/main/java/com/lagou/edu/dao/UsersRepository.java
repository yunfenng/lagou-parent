package com.lagou.edu.dao;

import com.lagou.edu.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Jaa
 * @Date 2022/8/7 23:02
 * @Description
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findUsersByUsername(String username);
}
