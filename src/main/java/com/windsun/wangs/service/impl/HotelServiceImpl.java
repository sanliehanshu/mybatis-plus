package com.windsun.wangs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windsun.wangs.entry.Hotel;
import com.windsun.wangs.mapper.HotelMapper;
import com.windsun.wangs.service.HotelService;
import org.springframework.stereotype.Service;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/30 09:55
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
    /**
     * 插入
     *
     * @param hotel
     */
    @Override
    public void insertHotel(Hotel hotel) {
        baseMapper.insert(hotel);
    }
}
