package com.moon.concurrent;

import java.util.concurrent.*;

public class ForkJionDame {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future submit = executorService.submit(new JionTask());
        System.out.println("收到返回结果"+submit.get());
        executorService.shutdown();
    }
}

class JionTask implements Callable {


    @Override
    public Object call() throws Exception {
        String str=Thread.currentThread().getName()+"执行完成";
        System.out.println(str);
        TimeUnit.SECONDS.sleep(5);
        return str;

    }
}