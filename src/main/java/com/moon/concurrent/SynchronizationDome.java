package com.moon.concurrent;

public class SynchronizationDome {


    public static void main(String[] args) {
        /**
         * 创建同一个锁
         */
        SynchronizationDome dome=new SynchronizationDome();
        new Thread(()->{
            game();
        },"thread0").start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            dome.watchTv();
        },"thread1").start();
    }
    public  static synchronized  void game() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":玩游戏");
    }

    public  synchronized void watchTv() {
        System.out.println(Thread.currentThread().getName()+":看电视");
    }
}


