package com.windsun.wangs.thread;

import java.io.Serializable;

/**
 * @Author：wangsheng
 * @Description：饿汉式单例
 * @Date：2022/6/5 15:36
 */
public class Singleton1 implements Serializable {



    private Singleton1(){
        //防止反射再创建对象
        if(singleton!=null){
            throw new RuntimeException("单例对象已经创建，不能重复创建");
        }
        System.out.println("private Singleton1");
    }

    private static Singleton1 singleton = new Singleton1();;

    public static  Singleton1 getSingleton(){
        return singleton;
    }

    public static void otherMethod(){
        System.out.println("这是其他方法！");
    }

    //解决反序列化创建新的对象，从而破坏单例
    private Object readResolve(){
        return singleton;
    }


}
