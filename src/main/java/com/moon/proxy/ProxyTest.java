package com.moon.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        AnimalProxy animalProxy=new AnimalProxy();
        Animal instance = (Animal)animalProxy.getInstance(new Cat());
        instance.color("red");

    }
}
