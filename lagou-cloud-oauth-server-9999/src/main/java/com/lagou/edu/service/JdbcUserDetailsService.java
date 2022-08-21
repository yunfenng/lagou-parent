package com.lagou.edu.service;

import com.lagou.edu.dao.UsersRepository;
import com.lagou.edu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author Jaa
 * @Date 2022/8/7 22:52
 * @Description
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 根据username查询出用户的所有信息, 封装成UserDetailslei类型的对象并返回, 至于密码，框架会自动匹配
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findUsersByUsername(username);
        return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
    }
}
