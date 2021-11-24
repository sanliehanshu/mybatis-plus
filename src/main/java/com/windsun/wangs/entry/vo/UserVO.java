package com.windsun.wangs.entry.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/11/22 21:52
 */
@Data
public class UserVO implements Serializable {


    private static final long serialVersionUID = -1564308176945691625L;

    private String name;

    private Integer source;
}
