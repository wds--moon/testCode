package com.moon.designpattern.function;


public class BenzFactory implements Factory {

    @Override
    public Car produce() {
        return new Benz();
    }
}
