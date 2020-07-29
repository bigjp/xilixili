package com.xilixili.controller;

import com.qf.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public interface UserController {
    @PostMapping("/findUserByUsername")
    User findUserByUsername(@RequestBody User user);
    @PostMapping("/addUser")
    boolean addUser(@RequestBody User user);
}
