package com.windsun.wangs.thread;

import java.io.Serializable;

/**
 * @Author：wangsheng
 * @Description：内部类的懒汉式单例
 * @Date：2022/6/5 15:36
 */
public class Singleton3 implements Serializable {

    private Singleton3() {
        System.out.println("private Singleton3");
    }

    //编译后是静态代码块
    private static class Holder {
        static Singleton3 singleton = new Singleton3();
    }

    public static Singleton3 getSingleton() {

        return Holder.singleton;
    }

    public static void otherMethod() {
        System.out.println("这是其他方法！");
    }
}
