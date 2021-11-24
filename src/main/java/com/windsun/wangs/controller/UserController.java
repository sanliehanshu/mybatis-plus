package com.windsun.wangs.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.windsun.wangs.entry.User;
import com.windsun.wangs.entry.vo.UserVO;
import com.windsun.wangs.mapper.UserMapper;
import com.windsun.wangs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 10:56
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/add")
    public Object add(){
        User user = new User();
        user.setName(RandomUtil.randomString(8));
        user.setSource(RandomUtil.randomInt(361,750));
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/update")
    public Object add(Long id){
        User user = userService.getById(id);
        user.setName(RandomUtil.randomString(8));
        user.setSource(RandomUtil.randomInt(361,750));
        userService.updateById(user);
        return user;
    }

    @PostMapping("/list/{current}/{size}")
    public Object list(@PathVariable(name = "current") Long current, @PathVariable(name = "size") Long size,@RequestBody(required = false) UserVO userVO){
        return userService.selectPageList(new Page(current,size),userVO);
    }

    @GetMapping("/listAll")
    public Object listAll(){
        int i = 1/0;
        return mapper.listAll();
    }
}
