package com.lagou.edu.service.impl;

import com.lagou.edu.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author Jaa
 * @Date 2022/6/4 17:17
 * @Description
 */
// Source.class 里面就是对输出通道的封装（Spring Cloud Stream内置通道封装）
@EnableBinding(Source.class)
public class MessageProducerImpl implements IMessageProducer {

    // 将MessageChannel封装的对象Source注入使用，Source封装这output通道
    @Autowired
    private Source source;

    @Override
    public void sendMessage(String content) {
        // 向MQ中发送消息（并不是直接操作MQ，应该操作的是Spring Cloud Stream）
        // 使用通道向外发送消息（指的是Source里面的output通道）
        source.output().send(MessageBuilder.withPayload(content).build());
    }
}
