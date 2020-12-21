package com.moon.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(5);
        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(10)*1000);
                    System.out.println(Thread.currentThread().getName()+"等待其他人员到齐,在吃饭");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"thread"+i).start();
        }
        countDownLatch.await();//阻塞当前线程
        System.out.println("人已经到齐开始吃饭");
    }

}
