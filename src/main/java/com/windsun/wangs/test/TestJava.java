package com.windsun.wangs.test;

/**
 * @Author：wangsheng
 * @Description：Java 值传递和引用传递
 * 值传递(pass by value)：在调用函数时，将实际参数复制一份传递到函数中，
 * 这样在函数中对参数进行修改，就不会影响到原来的实际参数；
 *
 * 引用传递(pass by reference):在调用函数时，将实际参数的地址直接传递到函数中。
 * 这样在函数中对参数进行的修改，就会影响到实际参数；
 * @Date：2022/4/19 09:03
 */
public class TestJava {

    public static void main(String[] args) {
        String i ="aaa";
        String test = test(i);
        System.out.println(test);

        System.out.println(test(i));;
        System.out.println("调用后i的值："+i);
    }

    static String test(String i){
        i = "bbb";
        return i;
    }
}
