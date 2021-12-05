package com.windsun.wangs.config;

import com.windsun.wangs.constants.HotelRabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/12/5 21:59
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 声明交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(HotelRabbitMQConstants.HOTEL_EXCHANGE,true,false);
    }

    /**
     * 声明插入和修改队列
     * @return
     */
    @Bean
    public Queue insertQueue(){
        return new Queue(HotelRabbitMQConstants.HOTEL_INSERT_QUEUE);
    }

    /**
     * 声明删除队列
     * @return
     */
    @Bean
    public Queue deleteQueue(){
        return new Queue(HotelRabbitMQConstants.HOTEL_DELETE_KEY);
    }

    /**
     * 绑定交换机、队列和routingKey
     * @return
     */
    @Bean
    public Binding insertBinding(){
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(HotelRabbitMQConstants.HOTEL_INSERT_KEY);
    }

    @Bean
    public Binding deleteBinding(){
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(HotelRabbitMQConstants.HOTEL_DELETE_KEY);
    }
}
