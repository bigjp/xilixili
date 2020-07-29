package com.xilixili.service.impl;

import com.qf.response.BaseResp;
import com.xilixili.service.CommentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public BaseResp comment(Comment comment) {
        BaseResp baseResp = new BaseResp();
        long currentTimeMillis = System.currentTimeMillis();
        String s = String.valueOf(currentTimeMillis);
        comment.setCdate(s);
        try{
            rabbitTemplate.convertAndSend("direct-exchange","comment",comment);
            baseResp.setCode(200);
            baseResp.setMessage("评论成功");
            return baseResp;
        }catch (Exception e){
            baseResp.setCode(400);
            baseResp.setMessage("评论失败");
            return baseResp;
        }
    }

}
