package com.moon.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadDemo implements Runnable {
    private ConcurrentHashMap<String,Object> chm;

    public ThreadDemo(ConcurrentHashMap<String, Object> chm) {
        this.chm = chm;
    }

    @Override
    public void run() {
        chm.put(Thread.currentThread().getName(),Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName());
    }
}
