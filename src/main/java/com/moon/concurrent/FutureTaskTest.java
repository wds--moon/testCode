package com.moon.concurrent;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> future = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i <6 ; i++) {
                    Thread.sleep(5000);//故意阻塞30s
                }
                return 0;
            }
        });
        executor.execute(future);
        System.out.println(future.get());
    }

}
