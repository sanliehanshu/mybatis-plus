package com.windsun.wangs.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.windsun.wangs.entry.User;
import com.windsun.wangs.entry.vo.UserVO;
import com.windsun.wangs.mapper.UserMapper;
import com.windsun.wangs.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 10:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /**
     * 插入user
     *
     * @param user
     */
    @Override
    public void insertUser(User user) {
        baseMapper.insert(user);
    }

    /**
     *
     * @param page
     * @param userVO
     * @return
     */
    @Override
    public Page<User> selectPageList(Page page, UserVO userVO) {
        return baseMapper.selectPageList(page,userVO);
    }
}
