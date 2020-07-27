package com.xilixili.controller.impl;

import com.qf.User;
import com.xilixili.controller.UserController;
import com.xilixili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Override
    public User findUserByUsername(@RequestBody User user) {
        return userService.findUserByUsername(user);
    }

    @Override
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
