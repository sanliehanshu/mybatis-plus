package com.windsun.wangs.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : LockTest
 * @Description : lock 可重用锁以及线程按照顺序执行；三个线程按照各个不同的循环次数依次执行
 * 
 * @Author : ws
 * @Date: 2022-01-16 21:49
 * @Version 1.0
 */
public class LockTest {

    public static void main(String[] args) {
        ShareResource share = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

    }
}

/**
 * 创建资源类
 */
class ShareResource {

    /**
     * 标识符判断哪个线程
     */
    private int flag = 1;

    /**
     * 创建lock
     */
    private Lock lock = new ReentrantLock();

    /**
     * 创举condition
     */
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 创建循环方法，定义循环逻辑
     *
     * @param loop 循环趟数，每组循环10次
     */
    public void print5(int loop) throws InterruptedException {
        // 加锁
        lock.lock();

        try {
            // while 防止线程虚假唤醒
            while (flag != 1) {
                c1.await();
            }
            for (int j = 1; j <= 5; j++) {
                System.out.println(Thread.currentThread().getName() + "循环 ：：" + j + "次，趟数：" + loop);
            }
            flag = 2;
            c2.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 创建循环方法，定义循环逻辑
     *
     * @param loop 循环趟数，每组循环10次
     */
    public void print10(int loop) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // while 防止线程虚假唤醒
            while (flag != 2) {
                c2.await();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println(Thread.currentThread().getName() + "循环 ：：" + j + "次，趟数：" + loop);
            }
            flag = 3;
            c3.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 创建循环方法，定义循环逻辑
     *
     * @param loop 循环趟数，每组循环10次
     */
    public void print15(int loop) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // while 防止线程虚假唤醒
            while (flag != 3) {
                c3.await();
            }
            for (int j = 1; j <= 15; j++) {
                System.out.println(Thread.currentThread().getName() + "循环 ：：" + j + "次，趟数：" + loop);
            }
            flag = 1;
            c1.signal();
            System.out.println();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

}
