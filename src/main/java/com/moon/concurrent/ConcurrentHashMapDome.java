package com.moon.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDome {


    public static void main(String[] args) {
        ConcurrentHashMap<String,Object> concurrentHashMap=new ConcurrentHashMap();

        new Thread(new ThreadDemo(concurrentHashMap),"thread-0").start();
        new Thread(new ThreadDemo(concurrentHashMap),"thread-1").start();
    }
}
