package com.xilixili.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CommentRabbitCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initMethod(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("消息唯一标识" + correlationData);
        System.out.println("消息是否发送成功：" + b);
        System.out.println("失败原因：" + s);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息主体："+message);
        System.out.println("应答码：" + i);
        System.out.println("原因描述：" + s);
        System.out.println("交换机名称：" + s1);
        System.out.println("routingkey：" + s2);
    }
}
