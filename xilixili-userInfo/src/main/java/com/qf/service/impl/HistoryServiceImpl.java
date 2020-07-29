package com.qf.service.impl;

import com.qf.Video;
import com.qf.dao.HistoryDao;
import com.qf.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;


    @Override
    public List<Video> showHistory(Integer uid) {
        return historyDao.showHistory(uid);
    }

    @Override
    public void deleteHistory(Integer vid) {
        historyDao.deleteHistory(vid);
    }
}
