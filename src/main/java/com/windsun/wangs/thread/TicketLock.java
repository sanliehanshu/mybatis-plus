package com.windsun.wangs.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : TicketLock
 * @Description : lock 公平锁和非公平锁
 * @Author : ws
 * @Date: 2022-01-23 21:29
 * @Version 1.0
 */
public class TicketLock {

    public static void main(String[] args) {

        station station = new station();
        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                station.sale();
            }
        }, "AA").start();


        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                station.sale();
            }
        }, "BB").start();

        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                station.sale();
            }
        }, "CC").start();

        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                station.sale();
            }
        }, "DD").start();
    }
}


class station {

    private int count = 30;

    /**
     * 创建lock,true 公平锁；false或不写为不公平锁
     */
    private Lock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();

        try {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + "：：卖了NO为：" + count + "，目前剩余" + --count);
            }
        } finally {
            lock.unlock();
        }
    }
}




