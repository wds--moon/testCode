package com.moon.concurrent;

public class SyncDome {

    private static int num=0;

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            new Thread(()->{
                add();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num);
    }
    public static synchronized void add(){
        num++;
    }
}
