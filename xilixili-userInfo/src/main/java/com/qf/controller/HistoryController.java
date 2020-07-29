package com.qf.controller;

import com.qf.Video;
import com.qf.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;


    //显示用户的观看历史
    @RequestMapping("/showHistory")
    public List<Video> showHistory(Integer uid){
        return historyService.showHistory(uid);
    }

    @RequestMapping("/deleteHistory")
    public String deleteHistory(Integer vid){
        historyService.deleteHistory(vid);
        return "删除浏览记录成功";
    }
}
