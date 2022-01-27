package com.windsun.wangs.thread;

/**
 * @ClassName : DeedLock
 * @Description : 死锁的演示
 * @Author : ws
 * @Date: 2022-01-24 22:37
 * @Version 1.0
 */
public class DeedLock {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, "a").start();


        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, "b").start();
    }
}
