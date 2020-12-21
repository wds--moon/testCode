package com.moon.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolExecutorMoon extends ThreadPoolExecutor {

    public ThreadPoolExecutorMoon(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExecutorMoon(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExecutorMoon(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExecutorMoon(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //实现自己的逻辑
        Map<String,Object> map=new HashMap<>();
        map.put("queueSize",getQueue().size());
        map.put("taskCount",getTaskCount());
        map.put("activeCount",getActiveCount());
        map.put("corePoolSize",getCorePoolSize());
        map.put("completedTaskCount",getCompletedTaskCount());
        map.put("largestPoolSize",getLargestPoolSize());
        map.put("maximumPoolSize",getMaximumPoolSize());
        System.out.println(t.getName()+"=>"+map.toString());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }
}
