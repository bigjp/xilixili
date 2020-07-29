package com.xilixili.service.impl;

import com.qf.User;
import com.xilixili.dao.UserDao;
import com.xilixili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUserByUsername(User user) {
        return userDao.findUserByUsername(user);
    }

    @Override
    public boolean addUser(User user) {
        int i = userDao.addUser(user);
        if(i>0){
            return true;
        }
        return false;
    }
}
