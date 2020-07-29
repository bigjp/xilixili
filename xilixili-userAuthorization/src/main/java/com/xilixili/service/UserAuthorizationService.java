package com.xilixili.service;

import com.qf.User;
import com.qf.response.BaseResp;

public interface UserAuthorizationService {
        String login(User user);

        String regis(User user);

}
