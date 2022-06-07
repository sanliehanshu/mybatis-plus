package com.windsun.wangs.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName : ArrayListThread
 * @Description : 演示arraylist 线程不安全
 * @Author : ws
 * @Date: 2022-01-19 10:47
 * @Version 1.0
 */
public class ArrayListThread {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                //System.out.println(list);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                //System.out.println(list);
                Singleton3.otherMethod();
                System.out.println( Singleton3.getSingleton());
            }, String.valueOf(i)).start();
        }
    }
}
