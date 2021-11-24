package com.windsun.wangs.entry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：wangsheng
 * @Description：用户实体类
 * @Date：2021/11/22 10:35
 */
@Data
@TableName("user")
public class User implements Serializable {


    private static final long serialVersionUID = 3872703855374438395L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer source;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteFlag;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
