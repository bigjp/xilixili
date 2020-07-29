package com.xilixili.service.impl;

import com.qf.Comment;
import com.xilixili.dao.CommentDao;
import com.xilixili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public boolean addComment(Comment comment) {
        int i = commentDao.addComment(comment);
        if(i>0){
            return true;
        }
        return false;
    }
}
