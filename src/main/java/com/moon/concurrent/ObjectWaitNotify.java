package com.moon.concurrent;

public class ObjectWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        Object ob = new Object();
        new Thread(() -> {
            synchronized (ob) {
                System.out.println("烧开水,等待客人来!,就开始泡茶");
                try {
                    /**
                     * 在java里面凡是存在需要阻塞的地方都会有InterruptedException,防止存在无妨中断线程等待
                     */
                    ob.wait();//这里可以加时间,如果时间到了,就自动释放锁资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("泡茶!");
            }
        }).start();
        Thread.sleep(2000);//暂停一下,等主线程先执行
        new Thread(() -> {
            synchronized (ob) {
                ob.notify();
                System.out.println("客人来了!");
            }
        }).start();
    }

}
