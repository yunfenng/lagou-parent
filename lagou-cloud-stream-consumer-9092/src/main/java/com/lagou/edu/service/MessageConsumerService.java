package com.lagou.edu.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @Author Jaa
 * @Date 2022/6/4 22:46
 * @Description
 */
@EnableBinding(Sink.class)
public class MessageConsumerService {

    @StreamListener(Sink.INPUT)
    public void receiveMessages(Message<String> message) {
        System.out.println("=========接收到的消息：" + message);
    }
}
