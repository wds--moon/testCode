package com.moon.concurrent;

public class ThreadTest {
    private  static int num=0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            num = 100;
        });
        thread.start();
        thread.join();//保证现在在主线程之前执行完成,结果对主线程可见,提供了happens before 原则
        System.out.println(num);
    }
}
