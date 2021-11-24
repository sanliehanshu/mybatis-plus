package com.windsun.wangs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.windsun.wangs.mapper")
public class WangsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangsApplication.class, args);
    }

}
