package com.qf.service;

import java.util.List;

public interface HistoryService {

    List<Video> showHistory(Integer uid);

    void deleteHistory(Integer vid);

}

