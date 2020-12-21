package com.moon.concurrent;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadLocalDemo {

    private static ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 1);
    private static ThreadLocal<String> str = ThreadLocal.withInitial(() -> "moon");

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Integer integer = num.get();
                integer += 5;
                num.set(integer);
                String s = str.get();
                str.set(s+" hello");
                System.out.println(Thread.currentThread().getName() + ",num=" + num.get()+",str="+str.get());
            }, "thread-" + i).start();
        }

    }
}
