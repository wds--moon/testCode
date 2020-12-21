package com.moon.proxy;

public interface Animal {

    default void before(){
        System.out.println("调用前............");
    }
    default void after(){
        System.out.println("调用后............");
    }
    void eat();

    void color(String color);
}
