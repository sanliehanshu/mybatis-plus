package com.windsun.wangs.listener;

import com.alibaba.fastjson.JSON;
import com.windsun.wangs.constants.HotelRabbitMQConstants;
import com.windsun.wangs.entry.Hotel;
import com.windsun.wangs.entry.HotelDoc;
import com.windsun.wangs.service.HotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/12/5 22:22
 */
@Component
public class HotelListener {

    @Autowired
    private HotelService hotelService;

    private RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
            HttpHost.create("http://192.168.121.129:9200")
    ));

    @RabbitListener(queues = HotelRabbitMQConstants.HOTEL_INSERT_QUEUE)
    public void listenHotelInsert(Long id) throws IOException {
        Hotel hotel = hotelService.getById(id);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 准备request对象
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        // 准备json文档
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        // 发送请求
        client.index(request, RequestOptions.DEFAULT);
    }
}
