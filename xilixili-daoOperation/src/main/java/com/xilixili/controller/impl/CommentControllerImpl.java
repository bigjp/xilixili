package com.xilixili.controller.impl;

import com.qf.Comment;
import com.xilixili.controller.CommentController;
import com.xilixili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentControllerImpl implements CommentController {
    @Autowired
    @Qualifier("commentService")
    CommentService commentService;
    @Override
    public boolean comment(Comment comment) {
        return commentService.addComment(comment);
    }
}
