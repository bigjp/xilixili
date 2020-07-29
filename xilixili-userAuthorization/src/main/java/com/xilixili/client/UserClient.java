package com.xilixili.client;

import com.qf.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eureka-daoOperation")
public interface UserClient {
    @PostMapping("/findUserByUsername")
    User findUserByUsername(@RequestBody User user);
    @PostMapping("/addUser")
    boolean addUser(@RequestBody User user);
}
