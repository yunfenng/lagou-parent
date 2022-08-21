package com.lagou.edu.config;

import com.lagou.edu.service.JdbcUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @Author Jaa
 * @Date 2022/7/5 22:55
 * @Description 该配置类，主要处理用户名和密码的校验等事宜
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsService jdbcUserDetailsService;

    /**
     * 注册一个认证管理器到容器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码对象（密码不进行加密处理）
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 处理用户名和密码事宜
     * 1) 客户端传递username和password参数到认证管理器
     * 2) 一般来说，username和password会存储在数据库中的而用户表中
     * 3) 根据用户表数据，验证当前传递过来的用户信息的合法性
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在这个方法中可以去关联数据库，当前我们先把用户信息配置在内存中
        // 实例化一个用户对象（相当于数据表中的一条用户记录）
        /*UserDetails user = new User("admin", "123456", new ArrayList<>());
        auth.inMemoryAuthentication()
                .withUser(user).passwordEncoder(passwordEncoder);*/

        auth.userDetailsService(jdbcUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
