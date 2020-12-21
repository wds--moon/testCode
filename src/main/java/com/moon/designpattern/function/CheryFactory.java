package com.moon.designpattern.function;


public class CheryFactory implements Factory {

    @Override
    public Car produce() {
        return new Chery();
    }
}
