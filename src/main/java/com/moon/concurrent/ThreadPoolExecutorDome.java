package com.moon.concurrent;

import java.util.concurrent.*;

public class ThreadPoolExecutorDome {


    public static void main(String[] args) {
        ThreadPoolExecutorMoon threadPoolExecutor = new ThreadPoolExecutorMoon(3, 8, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200), Executors.defaultThreadFactory(),
                new MyExecutionHandler());
        // threadPoolExecutor.prestartAllCoreThreads(); //是否开启初始化的时候直接创建所有核心线程数
        for (int i = 0; i < 1000; i++) {
            if(i%100==0){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threadPoolExecutor.execute(new Task());
        }
        threadPoolExecutor.shutdown();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行!");
    }
}

/**
 * 推荐自定义异常策略,这样直接记录失败的任务,当然也可以抛出异常日志记录
 */
class MyExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(executor.getThreadFactory().newThread(r).getName() + "异常策略需要记录日志,存储到mq或者db里面,后面专门写一个定时任务不断循环继续消费保证消息继续消费,同时提供日志查看,这里最好的解决方案是还需一个线程池监控可以重写一些方法");
        throw new RuntimeException(executor.getThreadFactory().newThread(r).getName() + "=>" + r.toString());
    }
}