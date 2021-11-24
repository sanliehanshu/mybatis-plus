package com.windsun.wangs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.windsun.wangs.entry.User;
import com.windsun.wangs.entry.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 10:50
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询的动态sql
     * @param userVO
     * @param page
     * @return
     */
    Page<User> selectPageList(Page page,@Param("param") UserVO userVO);


    List<User> listAll();
}
