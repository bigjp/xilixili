package com.xilixili.controller;

import com.qf.Comment;
import com.qf.response.BaseResp;
import com.xilixili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public BaseResp comment(@RequestBody Comment comment){
       /* long l = System.currentTimeMillis();
        String s = String.valueOf(l);
        System.out.println(s);
        long l1 = Long.parseLong(s);
        Date date = new Date(l1);
        System.out.println(date);
        String format = new SimpleDateFormat("MM月dd日 HH:mm:ss").format(date);
        System.out.println(format);*/

        return commentService.comment(comment);
    }
}
