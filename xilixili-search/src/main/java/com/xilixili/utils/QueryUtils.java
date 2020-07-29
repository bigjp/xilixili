package com.xilixili.utils;

import com.xilixili.pojo.EsSearchVideo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.Highlighter;
import java.io.IOException;
import java.util.Map;

@Component
public class QueryUtils {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public SearchHits byBooleanQuery(EsSearchVideo esSearchVideo) throws IOException {
        //指定查询索引库
        SearchRequest searchRequest = new SearchRequest(esSearchVideo.getEsIndex());
        //指定类型
        SearchRequest name = searchRequest.types(esSearchVideo.getEsType());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页
        searchSourceBuilder.from((esSearchVideo.getCurrentPage()-1)*esSearchVideo.getRowsPage());
        searchSourceBuilder.size(esSearchVideo.getRowsPage());
        //源字段过滤 相当于指定查询字段
        searchSourceBuilder.fetchSource(esSearchVideo.getSourceNames(),new String[]{});
        searchSourceBuilder.query(QueryBuilders.matchQuery(esSearchVideo.getQueryName(),esSearchVideo.getEsContent()).operator(Operator.OR));
        searchRequest.source(searchSourceBuilder);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(searchSourceBuilder.query());
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(0));
        searchSourceBuilder.sort(new FieldSortBuilder(esSearchVideo.getEsOrderName()).order(SortOrder.DESC));
        //设置高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color=red>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.fields().add(new HighlightBuilder.Field(esSearchVideo.getQueryName()));
        searchSourceBuilder.highlighter(highlightBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            String highName = (String) sourceAsMap.get(esSearchVideo.getQueryName());
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields!=null){
                HighlightField highlightField = highlightFields.get(esSearchVideo.getQueryName());
                if(highlightField!=null){
                    Text[] fragments = highlightField.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for(Text str:fragments){
                        stringBuffer.append(str.string());
                    }
                    highName=stringBuffer.toString();
                }
            }
            Integer vid = (Integer) sourceAsMap.get("vid");
            String description = (String) sourceAsMap.get("description");
            System.out.println(vid);
            System.out.println(highName);
            System.out.println(description);
            System.out.println(System.currentTimeMillis());
        }
        return hits;
            /*String type = (String) sourceAsMap.get("type");
            Integer clicks = (Integer) sourceAsMap.get("clicks");
            String collections = (String) sourceAsMap.get("collections");
            String videopath = (String) sourceAsMap.get("videopath");
            String date = (String) sourceAsMap.get("date");
            System.out.println(type);
            System.out.println(clicks);
            System.out.println(collections);
            System.out.println(videopath);
            System.out.println(date);*/
    }
}
