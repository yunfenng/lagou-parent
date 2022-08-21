package com.lagou.edu.test;

import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @Author Jaa
 * @Date 2022/5/25 10:36
 * @Description
 */
public class MyTest {

    @Test
    public void testTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
