package com.xilixili.service;

import com.qf.Comment;
import com.qf.response.BaseResp;

public interface CommentService {
    BaseResp comment(Comment comment);
}
