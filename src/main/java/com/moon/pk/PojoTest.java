package com.moon.pk;

import java.lang.reflect.Constructor;

public class PojoTest {
    public static void main(String[] args) {
        try {
            //反射是需要创建对象才有意义,那么构造方法就会执行,防止可以构造方法中动手脚,但是这个方法有个问题,实例化是创建出来了,只是不能反馈,内存开销还是在的
            Class cls = Class.forName("com.moon.pk.Singleton");
            Constructor constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object o1 = constructor.newInstance();
            Object o2 = constructor.newInstance();
            System.out.println(o1==o2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
