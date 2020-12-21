package com.moon.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadPoolDome{


    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(100);

    public static void main(String[] args) {
        ThreadPoolDome threadPoolDome = new ThreadPoolDome();
        threadPoolDome.add();//生产数据
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolDome.remove();
    }
    /**
     * 消费者
     */
    public void remove() {
        new Thread(()->{
            while (true) {
                try {
                    if(!arrayBlockingQueue.isEmpty()){
                        String take = arrayBlockingQueue.remove();
                        System.out.println(Thread.currentThread().getName()+"消费"+take);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread-0").start();
        new Thread(()->{
            while (true) {
                try {
                    if(!arrayBlockingQueue.isEmpty()){
                        String take = arrayBlockingQueue.remove();
                        System.out.println(Thread.currentThread().getName()+"消费"+take);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread-1").start();
    }

    /**
     * 生产者
     */
    public void add() {
        for (int i = 0; i < 50; i++) {
            System.out.println("生产数据=>" + i);
            arrayBlockingQueue.add(""+i);
        }
    }

}
