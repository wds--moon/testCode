package com.moon.proxy;

public class Cat implements Animal {

    @Override
    public void eat() {
        before();
        System.out.println("cat eat.........");
        after();
    }

    @Override
    public void color(String color) {
        System.out.println("cat ........."+color);
    }
}
