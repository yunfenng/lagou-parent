package com.lagou.edu.controller;

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
@RequestMapping("/others")
public class OthersController {

    @GetMapping("/test")
    public String findResumeOpenState() {
        return "others/test!";
    }

}
