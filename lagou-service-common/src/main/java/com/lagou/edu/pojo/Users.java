package com.lagou.edu.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Jaa
 * @Date 2022/8/7 22:57
 * @Description
 */
@Data
@Entity
@Table(name="users")
public class Users {
    @Id
    private Long id;
    private String username;
    private String password;
}
