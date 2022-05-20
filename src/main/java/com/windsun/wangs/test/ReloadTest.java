package com.windsun.wangs.test;

/**
 * @Author：wangsheng
 * @Description：方法重载和重写
 * @Date：2021/12/31 20:56
 */
public class ReloadTest {

    public String test() {
        return "1";
    }


    public static void main(String[] args) {
        String s = "hello";
        String s2 = "he" + new String("llo");
        System.out.println(s == s2);
    }

    //  'test()' clashes with 'test()'; both methods have same erasure
    // 'test()' is already defined in 'com.windsun.wangs.test.ReloadTest'
    /*public int test() {
        return 1;
    }*/
}
