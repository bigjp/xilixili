package com.xilixili.service;

import com.qf.User;

public interface UserAuthorizationService {
        String login(User user);

        String regis(User user);
}
