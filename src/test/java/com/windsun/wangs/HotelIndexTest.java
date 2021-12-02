package com.windsun.wangs;

import com.alibaba.fastjson.JSON;
import com.windsun.wangs.entry.Hotel;
import com.windsun.wangs.entry.HotelDoc;
import com.windsun.wangs.service.HotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.windsun.wangs.constants.HotelIndexConstants.MAPPING_TEMPLATE;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/30 10:00
 */
@SpringBootTest
public class HotelIndexTest {

    private RestHighLevelClient client;

    @Autowired
    private HotelService hotelService;

    // 创建索引库
    @Test
    void testCreateIndex() throws IOException {
        // 1.准备request PUT/hotel
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 2.准备请求参数
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    // 批量插入数据
    @Test
    void testBulkRequest() throws IOException {
        // 1.准备request PUT/hotel
        BulkRequest request = new BulkRequest("hotel");
        // 2.准备数据
        List<Hotel> list = hotelService.list();
        for (Hotel hotel : list) {
            HotelDoc hotelDoc = new HotelDoc(hotel);

            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

    // 查询所有
    @Test
    void testMatchAll() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.matchAllQuery());
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    // 高亮查询
    @Test
    void testHighlight() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.matchQuery("all", "汉庭"));
        // mame高亮，不精确匹配，只要查询出来就高亮
        // 设置为 true 以仅在查询匹配该字段时才突出显示该字段。默认为 false 意味着术语在所有请求的字段上都突出显示，
        // 无论查询是否特别匹配它们。
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    // 分页查询
    @Test
    void testPageMatchAll() throws IOException {
        // 页码，每页大小
        int page = 2, size = 5;
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.matchAllQuery());
        // 分页，也可以链式编程
        request.source().from((page - 1) * size).size(5).sort("price", SortOrder.ASC);
        // 价格升序
        //request.source().sort("price", SortOrder.ASC);
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    // 条件查询
    @Test
    void testMatch() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.matchQuery("all", "如家"));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    // 精确查询
    @Test
    void testTermQuery() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.termQuery("city", "上海"));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    // 范围查询
    @Test
    void testRangeQuery() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备dsl
        request.source().query(QueryBuilders.rangeQuery("price").gte(100).lte(150));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    // 复合查询
    @Test
    void testBoolQuery() throws IOException {
        // 1.准备request
        SearchRequest request = new SearchRequest("hotel");
        // 创建布尔查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 添加must条件
        boolQuery.must(QueryBuilders.termQuery("city", "上海"));
        // 添加filter条件：小于等于250
        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(150));
        // 2.准备dsl
        request.source().query(boolQuery);
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        handleResponse(response);
    }

    private void handleResponse(SearchResponse response) {
        // 4.解析结果
        SearchHits searchHits = response.getHits();
        // 4.1 获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("总条数：" + total);
        // 4.2 文档数组
        SearchHit[] hits = searchHits.getHits();
        // 遍历
        for (SearchHit hit : hits) {
            // 获取文档source
            String sourceAsString = hit.getSourceAsString();
            // 反序列化
            HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                // 根据字段名称获取高亮结果
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    // 获取高亮值
                    String name = highlightField.getFragments()[0].toString();
                    hotelDoc.setName(name);
                }
            }
            System.out.println(hotelDoc.toString());
        }
    }

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.121.129:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }

}
