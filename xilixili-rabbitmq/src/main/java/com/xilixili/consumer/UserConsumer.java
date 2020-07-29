package com.xilixili.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserConsumer {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${simpleMailMessage.from}")
    String from;
    @Value("${simpleMailMessage.subject}")
    String subject;
    @Value("${simpleMailMessage.text}")
    String text;
    @Value("${redis.codeExpired}")
    int codeExpired;

    @RabbitListener(queues = "emailQueue")
    public void sendEmail(String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

        redisTemplate.opsForValue().set("checkCode",text);
        redisTemplate.expire("checkCode",codeExpired, TimeUnit.SECONDS);
    }

}
