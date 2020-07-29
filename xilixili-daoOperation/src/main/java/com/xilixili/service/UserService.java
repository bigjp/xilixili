package com.xilixili.service;

import com.qf.User;

public interface UserService {

    User findUserByUsername(User user);
    boolean addUser(User user);
}
