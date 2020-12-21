package com.moon.designpattern.ab;

public class BlackCarFactory implements Factory {
    @Override
    public Car produce() {
        return new Chery();
    }

    @Override
    public Bag bag() {
        return new BlackBag();
    }
}
