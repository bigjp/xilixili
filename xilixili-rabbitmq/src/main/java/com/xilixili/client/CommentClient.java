package com.xilixili.client;

import com.qf.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eureka-daoOperation")
public interface CommentClient {
    @PostMapping("/comment")
    boolean comment(@RequestBody Comment comment);
}
