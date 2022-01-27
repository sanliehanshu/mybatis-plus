package com.windsun.wangs.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : ArrayListSource
 * @Description : list 源码探究
 * @Author : ws
 * @Date: 2022-01-09 21:43
 * @Version 1.0
 */
public class ArrayListSource {

    public static void main(String[] args) {

        List list = new ArrayList();

        list.add("王升");
        list.add("杨美杰");
        list.add(1,"王心怡");

        //System.out.println("list：" + list);

        int[] test = {1,2,3,4,5};
        int[] copyOf = Arrays.copyOf(test, 15);
        //System.out.println(Arrays.toString(copyOf));


        for (int i = 0; i < 7; i++) {
            list.add(i);
        }
        list.add(null);
        System.out.println(list);
    }
}
