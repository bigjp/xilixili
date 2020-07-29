package com.qf.service;

import com.qf.Video;

import java.util.List;

public interface HistoryService {

    List<Video> showHistory(Integer uid);

    void deleteHistory(Integer vid);

}

