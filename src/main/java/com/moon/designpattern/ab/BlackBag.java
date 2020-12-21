package com.moon.designpattern.ab;

public class BlackBag implements Bag {
    @Override
    public void get() {
        System.out.println("黑色的书包!");
    }
}
