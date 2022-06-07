package com.windsun.wangs.test;

import cn.hutool.core.date.DateUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2022/5/20 23:30
 */
public class DateTest {
    public static void main(String[] args) {
        //LocalDateTime -->> Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(instant);
        System.out.println("LocalDateTime -->> Date："+from);


        //hutool
        String format = DateUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("hutool："+format);

        //Date -->> LocalDateTime
        LocalDateTime localDateTime1 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Date -->> LocalDateTime："+localDateTime1);

        //Str -->> LocalDateTime
        // DateTimeFormatter
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
        // str -> LocalDateTime
        LocalDateTime time2 = LocalDateTime.parse("2022-01-01 14:11:13", formatter2);
        // Print
        System.out.println("Str -->> LocalDateTime："+formatter2.format(time2));//2022-04-13 16:01:42

        //LocalDateTime -> str
        System.out.println(formatter2.format(LocalDateTime.now()));

    }
}
