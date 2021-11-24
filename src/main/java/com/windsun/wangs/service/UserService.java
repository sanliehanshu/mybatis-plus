package com.windsun.wangs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.windsun.wangs.entry.User;
import com.windsun.wangs.entry.vo.UserVO;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 10:52
 */
public interface UserService extends IService<User> {

    /**
     * 插入user
     * @param user
     */
    void insertUser(User user);

    /**
     *
     * @param page
     * @param userVO
     * @return
     */
    Page<User> selectPageList(Page page, UserVO userVO);
}
