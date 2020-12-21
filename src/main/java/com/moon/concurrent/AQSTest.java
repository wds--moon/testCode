package com.moon.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {


    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {

        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }
}
