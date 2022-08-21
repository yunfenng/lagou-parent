package com.lagou.edu.test;

import com.lagou.edu.StreamProducerApplication9090;
import com.lagou.edu.service.IMessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author Jaa
 * @Date 2022/6/4 17:30
 * @Description
 */
@SpringBootTest(classes = StreamProducerApplication9090.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageProducerTest {

    @Autowired
    private IMessageProducer iMessageProducer;

    @Test
    public void testSendMessage() {
        iMessageProducer.sendMessage("hello lagou-world");
    }
}
