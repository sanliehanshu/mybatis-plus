package com.windsun.wangs.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName : CountDownLatchDemo
 * @Description : 计数器：所有同学自习结束后，班长才能锁门走人
 * @Author : ws
 * @Date: 2022-01-27 21:13
 * @Version 1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i < 7; i++) {
            // lambda effectively final
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI + " 号同学走人了");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("班长锁门走人了");
    }
}
