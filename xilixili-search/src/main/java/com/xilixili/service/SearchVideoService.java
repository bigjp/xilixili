package com.xilixili.service;

import com.qf.Video;
import com.xilixili.pojo.EsSearchVideo;

import java.util.List;

public interface SearchVideoService {
   List<Video> search(EsSearchVideo esSearchVideo);
}
