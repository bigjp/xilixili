package com.xilixili.consumer;

import com.xilixili.client.CommentClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer {
    @Autowired
    CommentClient commentClient;
    @Autowired
    RedisTemplate redisTemplate;

    @RabbitListener(queues = "commentQueue")
    public void comment(Comment comment){
        boolean comment1 = commentClient.comment(comment);
        redisTemplate.opsForValue().set("comment",comment);
    }
}
