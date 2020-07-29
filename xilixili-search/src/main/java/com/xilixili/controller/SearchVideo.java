package com.xilixili.controller;

import com.xilixili.pojo.EsSearchVideo;
import com.xilixili.service.SearchVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchVideo {
    @Autowired
    @Qualifier("searchVideoService")
    SearchVideoService searchVideoService;

    @PostMapping("/searchVideo")
    public List<Video> searchVideo(@RequestBody EsSearchVideo esSearchVideo){
        System.out.println(esSearchVideo);
        return searchVideoService.search(esSearchVideo);
    }
}
