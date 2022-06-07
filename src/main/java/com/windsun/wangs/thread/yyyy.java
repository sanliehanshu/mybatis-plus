package com.windsun.wangs.thread;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2022/6/5 15:43
 */
public class yyyy {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                System.out.println( Singleton1.getSingleton());
            }, String.valueOf(i)).start();
        }


        Constructor<?> constructor = Singleton1.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println("反射创建对象"+constructor.newInstance());

    }
}
