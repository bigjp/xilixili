package com.xilixili.controller;

import com.qf.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentController {

    @PostMapping("/comment")
    boolean comment(@RequestBody Comment comment);
}
