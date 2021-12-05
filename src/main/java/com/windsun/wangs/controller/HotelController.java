package com.windsun.wangs.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.windsun.wangs.constants.HotelRabbitMQConstants;
import com.windsun.wangs.entry.Hotel;
import com.windsun.wangs.service.HotelService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 10:56
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/add")
    public Object add() {
        Hotel hotel = new Hotel();
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        hotel.setId(id);
        hotel.setAddress(RandomUtil.randomString(18));
        hotel.setName(RandomUtil.randomString(8));
        hotel.setScore(RandomUtil.randomInt(0, 50));
        hotel.setBrand(RandomUtil.randomString(4));
        hotel.setCity(RandomUtil.randomString(5));
        hotel.setStarName(RandomUtil.randomString(5));
        hotel.setBusiness(RandomUtil.randomString(5));
        hotel.setLatitude(RandomUtil.randomDouble(0.0, 90.0) + "");
        hotel.setLongitude(RandomUtil.randomDouble(90, 180.0) + "");
        hotel.setPrice(RandomUtil.randomInt(361, 750));
        hotelService.save(hotel);
        amqpTemplate.convertAndSend(HotelRabbitMQConstants.HOTEL_EXCHANGE, HotelRabbitMQConstants.HOTEL_INSERT_KEY, hotel.getId());
        return hotel;
    }
    //
    //@RequestMapping("/update")
    //public Object add(Long id){
    //    User user = userService.getById(id);
    //    user.setName(RandomUtil.randomString(8));
    //    user.setSource(RandomUtil.randomInt(361,750));
    //    userService.updateById(user);
    //    return user;
    //}
}
