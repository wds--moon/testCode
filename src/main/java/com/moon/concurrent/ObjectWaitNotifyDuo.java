package com.moon.concurrent;

public class ObjectWaitNotifyDuo {

    public static void main(String[] args) throws InterruptedException {
        Object ob = new Object();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (ob) {
                    System.out.println(Thread.currentThread().getName()+"点菜等待吃饭!");
                    try {
                        ob.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"吃完结账!");
                }
            },"thread"+i).start();
        }
        Thread.sleep(2000);//暂停一下,等主线程先执行
        new Thread(()->{
            synchronized (ob) {
                ob.notifyAll();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("上菜结束!");
            }
        },"唤醒线程").start();
    }
}
