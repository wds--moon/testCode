package com.moon.proxy;

public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy=new CglibProxy();
        People people = (People)cglibProxy.getInstance(new People());
        people.eat();
   }
}
