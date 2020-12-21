package com.moon.concurrent;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDome {

    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(5,true);

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueueDome arrayBlockingQueueDome=new ArrayBlockingQueueDome();
        for (int i = 0; i <20 ; i++) {
            arrayBlockingQueueDome.save();
        }
    }

    public  void save() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        arrayBlockingQueue.put(uuid);
        System.out.println("生产了一个:"+arrayBlockingQueue.size());
    }

    public  void remove() throws InterruptedException {
        String take = arrayBlockingQueue.take();
        System.out.println("消费了一个,剩余:"+arrayBlockingQueue.size());
    }
}
