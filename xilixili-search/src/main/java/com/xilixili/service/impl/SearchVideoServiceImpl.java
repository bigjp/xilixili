package com.xilixili.service.impl;

import com.xilixili.pojo.EsSearchVideo;
import com.xilixili.service.SearchVideoService;
import com.xilixili.utils.QueryUtils;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("searchVideoService")
public class SearchVideoServiceImpl implements SearchVideoService {
    @Autowired
    QueryUtils queryUtils;

    @Override
    public List<Video> search(EsSearchVideo esSearchVideo) {
        List<Video> list = new ArrayList<>();
        try {
            SearchHits searchHits = queryUtils.byBooleanQuery(esSearchVideo);
            for(SearchHit hit:searchHits){
                Video video = new Video();
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String title = (String) sourceAsMap.get(esSearchVideo.getQueryName());
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if(highlightFields!=null){
                    HighlightField highlightField = highlightFields.get(esSearchVideo.getQueryName());
                    if(highlightField!=null){
                        Text[] fragments = highlightField.getFragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        for(Text str:fragments){
                            stringBuffer.append(str.string());
                        }
                        title=stringBuffer.toString();
                    }
                }
                Integer vid = (Integer) sourceAsMap.get("vid");
//                String title = (String) sourceAsMap.get("title");
                String description = (String) sourceAsMap.get("description");
                String type = (String) sourceAsMap.get("type");
                Integer clicks = (Integer) sourceAsMap.get("clicks");
                String collections = (String) sourceAsMap.get("collections");
                String videopath = (String) sourceAsMap.get("videopath");
                String date = (String) sourceAsMap.get("date");
                video.setVid(vid);
                video.setTitle(title);
                video.setType(type);
                video.setClicks(clicks);
                video.setCollections(collections);
                video.setVideoPath(videopath);
//                long currentTimeMillis = System.currentTimeMillis();
//                String sdate = String.valueOf(currentTimeMillis);
                video.setDate(date);
                video.setDescription(description);
                list.add(video);
            }
        } catch (IOException e) {
            return null;
        }
        return list;
    }
}
