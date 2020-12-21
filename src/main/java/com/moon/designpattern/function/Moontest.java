package com.moon.designpattern.function;

class QQFactory implements Factory{

    @Override
    public Car produce() {
        return new QQ();
    }
}

class QQ implements Car{

    @Override
    public void create() {
        System.out.println("生产QQ汽车");
    }
}

public class Moontest {

    public static void main(String[] args) {
        FactoryUtil.insert(new QQFactory());
    }
}
