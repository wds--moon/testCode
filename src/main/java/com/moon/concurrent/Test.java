package com.moon.concurrent;

public class Test {
    public volatile static   int flg=100;
    public static void main(String[] args) {
        System.out.println(add());
    }
    public static  int add(){
       return ++flg;
    }

}
