package com.moon.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDome {


    static Lock lock = new ReentrantLock();
    static Condition read = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionDome demo=new ConditionDome();
        new Thread(()->{
            demo.eat();
        },"饭桶1").start();
        new Thread(()->{
            demo.eat();
        },"饭桶2").start();
        Thread.sleep(1000);
        new Thread(()->{
            demo.cook();
        },"阿姨").start();

    }

    public  void cook() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"............做饭准备中..........");
            read.signalAll();
            System.out.println(Thread.currentThread().getName()+"............已经准备好了..........");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void eat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"等待吃饭............");
            read.await();//等待饭好了才能吃
            System.out.println(Thread.currentThread().getName()+"吃完了............");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}
