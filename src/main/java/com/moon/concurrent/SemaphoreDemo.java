package com.moon.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {



    public static void main(String[] args) {
        /**
         * 一次任务只能5个通过
         */
        Semaphore semaphore=new Semaphore(5);

        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"=>"+"通过");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"thread"+i).start();
        }
    }

}
