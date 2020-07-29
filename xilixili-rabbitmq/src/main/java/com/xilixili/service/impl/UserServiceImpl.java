package com.xilixili.service.impl;

import com.qf.response.BaseResp;
import com.xilixili.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public BaseResp sendEmail(String email) {
        BaseResp baseResp = new BaseResp();
        try{
            rabbitTemplate.convertAndSend("direct-exchange","sendEmail",email);
            baseResp.setCode(200);
            baseResp.setMessage("已发送邮箱");
            return baseResp;
        }catch (Exception e){
            baseResp.setCode(400);
            baseResp.setMessage("发送失败，请重新获取");
            return baseResp;
        }
    }
}
