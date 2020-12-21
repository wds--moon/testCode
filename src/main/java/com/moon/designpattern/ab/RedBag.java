package com.moon.designpattern.ab;

public class RedBag implements Bag {
    @Override
    public void get() {
        System.out.println("红色的书包!");
    }
}
