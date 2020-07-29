package com.xilixili.controller;

import com.qf.response.BaseResp;
import com.xilixili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/sendEmail/{email}")
    public BaseResp sendEmail(@PathVariable("email") String email){
        return userService.sendEmail(email);
    }
}
