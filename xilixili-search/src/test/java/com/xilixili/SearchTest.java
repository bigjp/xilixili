package com.xilixili;

import com.qf.Video;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchTest {
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    RestClient restClient;

    //query source
    @Test
    public void testQuerySource() throws IOException {
        //指定查询索引库
        SearchRequest searchRequest = new SearchRequest("java_client_index");
        //指定类型
        SearchRequest name = searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //源字段过滤 相当于指定查询字段
        searchSourceBuilder.fetchSource(new String[]{"name","description"},new String[]{});
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            System.out.println("索引库："+hit.getIndex());
            System.out.println("类型："+hit.getType());
            System.out.println("id:"+hit.getId());
            System.out.println("匹配值："+hit.getScore());
            System.out.println(hit.getSourceAsString());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.get("name"));
            System.out.println(sourceAsMap.get("description"));
            System.out.println(sourceAsMap.get("studymodel"));
        }
    }

    //query source 分页
    @Test
    public void testQueryFrom() throws IOException {
        //指定查询索引库
        SearchRequest searchRequest = new SearchRequest("java_client_index");
        //指定类型
        SearchRequest name = searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1);
        //源字段过滤 相当于指定查询字段
        searchSourceBuilder.fetchSource(new String[]{"name","description"},new String[]{});
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            System.out.println("索引库："+hit.getIndex());
            System.out.println("类型："+hit.getType());
            System.out.println("id:"+hit.getId());
            System.out.println("匹配值："+hit.getScore());
            System.out.println(hit.getSourceAsString());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.get("name"));
            System.out.println(sourceAsMap.get("description"));
            System.out.println(sourceAsMap.get("studymodel"));
        }
    }
    //esIndex esType video content esOrderName currentPage rowsPage
    @Test
    public void testMatchQuery() throws IOException {
        //指定查询索引库
        SearchRequest searchRequest = new SearchRequest("java_client_index");
        //指定类型
        SearchRequest name = searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //源字段过滤 相当于指定查询字段
        searchSourceBuilder.fetchSource(new String[]{"name","description"},new String[]{});
        searchSourceBuilder.query(QueryBuilders.matchQuery("description","开发").operator(Operator.OR));
        searchRequest.source(searchSourceBuilder);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(searchSourceBuilder.query());
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(0));
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC));

        SearchResponse search = restHighLevelClient.search(searchRequest);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            System.out.println("索引库："+hit.getIndex());
            System.out.println("类型："+hit.getType());
            System.out.println("id:"+hit.getId());
            System.out.println("匹配值："+hit.getScore());
            System.out.println(hit.getSourceAsString());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.get("name"));
            System.out.println(sourceAsMap.get("description"));
            System.out.println(sourceAsMap.get("studymodel"));
        }
    }

    @Test
    public void testBooleanQuery() throws IOException {
        Video video = new Video();
        List<Video> list = new ArrayList<>();
        //指定查询索引库
        SearchRequest searchRequest = new SearchRequest("video_index");
        //指定类型
        SearchRequest name = searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        //源字段过滤 相当于指定查询字段
        searchSourceBuilder.fetchSource(new String[]{"vid","title","description"},new String[]{});
        searchSourceBuilder.query(QueryBuilders.matchQuery("title","碧海").operator(Operator.OR));
        searchRequest.source(searchSourceBuilder);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(searchSourceBuilder.query());
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(0));
        searchSourceBuilder.sort(new FieldSortBuilder("vid").order(SortOrder.DESC));

        SearchResponse search = restHighLevelClient.search(searchRequest);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Integer vid = (Integer) sourceAsMap.get("vid");
            String title = (String) sourceAsMap.get("title");
            String description = (String) sourceAsMap.get("description");
            video.setVid(vid);
            video.setTitle(title);
            video.setDescription(description);
            System.out.println(video);
            list.add(video);
        }
        System.out.println("=======================");
        System.out.println(list);
//            System.out.println(vid);
//            System.out.println(title);
//            System.out.println(description);
//            String type = (String) sourceAsMap.get("type");
//            Integer clicks = (Integer) sourceAsMap.get("clicks");
//            String collections = (String) sourceAsMap.get("collections");
//            String videopath = (String) sourceAsMap.get("videopath");
//            String date = (String) sourceAsMap.get("date");
//            System.out.println(type);
//            System.out.println(clicks);
//            System.out.println(collections);
//            System.out.println(videopath);
//            System.out.println(date);
    }
}
