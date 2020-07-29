package com.qf.controller;

import com.qf.pojo.BaseResp;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
public class Elasticsearch {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @RequestMapping("/findvideo")
    public BaseResp findvideo(String type,Integer currentpage,Integer size){
        SearchRequest searchRequest = new SearchRequest("project");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询类型为type的视频
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(type,"type","title","description").minimumShouldMatch("50%").field("type",10));
//        searchSourceBuilder.query(QueryBuilders.termQuery("type",type));
        //分页
        int from=(currentpage-1)*size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchRequest.source(searchSourceBuilder);
        List<Video> videos=new ArrayList<>();
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest);
            SearchHits hits = search.getHits();
            SearchHit[] hits1 = hits.getHits();
            System.out.println(hits1.length);
            for(SearchHit hit:hits1){
                System.out.println("==========4");
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Integer vid = Integer.parseInt(hit.getId());
                String title = (String)sourceAsMap.get("title");
                String description=(String) sourceAsMap.get("description");
                String type1 = (String)sourceAsMap.get("type");
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String)sourceAsMap.get("date"));
                Integer clicks = (Integer)sourceAsMap.get("clicks");
                String collentions=(String)sourceAsMap.get("collentions");
                Integer great=(Integer)sourceAsMap.get("great");
                String videopath =(String) sourceAsMap.get("videopath");
                Video video=new Video();
                video.setVid(vid);
                video.setTitle(title);
                video.setDescription(description);
                video.setType(type1);
                video.setDate(date);
                video.setClicks(clicks);
                video.setCollentions(collentions);
                video.setGreat(great);
                video.setVideopath(videopath);
                videos.add(video);
                System.out.println(sourceAsMap+"=========="+title);
            }
        } catch (Exception e) {
            System.out.println("==========5");
            e.printStackTrace();
        }
        System.out.println("==========1");
        BaseResp baseResp=new BaseResp();
        baseResp.setList(videos);
        baseResp.setCode(1);
        baseResp.setMessage("推荐查询成功");
        return baseResp;
    }

}
