package com.windsun.wangs.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2022/2/10 10:01
 */
public class ArrayListTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.set(3,"s");

        System.out.println(list);


        Map<String, String> map = new HashMap<String, String>(32);
        map.put("hollis", "hollischuang");

        /**
         * ①、得到 Class 的三种方式
         *
         * 复制代码
         *  1 //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
         *  2 //  类型的对象，而我不知道你具体是什么类，用这种方法
         *  3 　　Person p1 = new Person();
         *  4 　　Class c1 = p1.getClass();
         *  5
         *  6 //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
         *  7 //  这说明任何一个类都有一个隐含的静态成员变量 class
         *  8 　　Class c2 = Person.class;
         *  9
         * 10 //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
         * 11 //   但可能抛出 ClassNotFoundException 异常
         * 12 　　Class c3 = Class.forName("com.ys.reflex.Person");
         * 复制代码
         * 需要注意的是：一个类在 JVM 中只会有一个 Class 实例,即我们对上面获取的 c1,c2,c3进行 equals 比较，发现都是true
         *
         * ②、通过 Class 类获取成员变量、成员方法、接口、超类、构造方法等
         *
         * 查阅 API 可以看到 Class 有很多方法：
         *
         * 　　getName()：获得类的完整名字。
         * 　　getFields()：获得类的public类型的属性。
         * 　　getDeclaredFields()：获得类的所有属性。包括private 声明的和继承类
         * 　　getMethods()：获得类的public类型的方法。
         * 　　getDeclaredMethods()：获得类的所有方法。包括private 声明的和继承类
         * 　　getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
         * 　　getConstructors()：获得类的public类型的构造方法。
         * 　　getConstructor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes 参数指定构造方法的参数类型。
         * 　　newInstance()：通过类的不带参数的构造方法创建这个类的一个对象。
         */
        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(mapType.newInstance()));

        Field size = mapType.getDeclaredField("size");
        Field isEmpty = mapType.getDeclaredField("modCount");
        isEmpty.setAccessible(true);
        size.setAccessible(true);
        System.out.println("size : " + size.get(map));
        System.out.println("isEmpty : " + isEmpty.get(map));
    }
}
