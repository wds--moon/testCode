package com.moon.designpattern.ab;

public class RedCarFactory implements Factory {
    @Override
    public Car produce() {
        return new Benz();
    }

    @Override
    public Bag bag() {
        return new RedBag();
    }
}
