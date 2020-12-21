package com.moon.designpattern.function;

public class FactoryUtil {

    /**
     * 新增车
     * @param factory
     */
    public static void insert(Factory factory){
        System.out.println("工厂开始建造车");
        factory.produce().create();
        System.out.println("工厂建造车完成");
    }
}
