package com.xilixili.controller;

import com.qf.User;
import com.xilixili.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAuthorizationController {
    @Autowired
    @Qualifier("userAuthorizationService")
    UserAuthorizationService userAuthorizationService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userAuthorizationService.login(user);
    }

    @PostMapping("/regis")
    public String regis(@RequestBody User user){
        return userAuthorizationService.regis(user);
    }

}
