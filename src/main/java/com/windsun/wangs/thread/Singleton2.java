package com.windsun.wangs.thread;

import java.io.Serializable;

/**
 * @Author：wangsheng
 * @Description：dcl 双重锁懒汉式单例
 * @Date：2022/6/5 15:36
 */
public class Singleton2 implements Serializable {

    //volatile 可见行和有序性，此时是有序性，volatile不能保证原子性
    private static volatile Singleton2 singleton = null;

    private Singleton2(){
        System.out.println("private Singleton1");
    }



    public static Singleton2 getSingleton(){
        //1.线程一和线程二同时进来
        if (singleton == null) {
            //2.假如线程二先得到锁，此时线程一等待
            //4.此时线程一得到锁。往下执行
            synchronized (Singleton2.class) {
                //5.线程一发现 singleton已经存在，故结束
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
            //3.线程二结束
        }
        return singleton;
    }

    public static void otherMethod(){
        System.out.println("这是其他方法！");
    }
}
