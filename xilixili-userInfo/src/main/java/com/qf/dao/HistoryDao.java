package com.qf.dao;

import com.qf.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface HistoryDao {
    List<Video> showHistory(Integer uid);

    void deleteHistory(Integer vid);
}
