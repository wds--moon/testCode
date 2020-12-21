package com.moon.concurrent;

public class VolatileDome {
    public static volatile boolean flag=true;

    public static void main(String[] args) {
        new Thread(()->{
            while (flag){
                System.out.println("++++++++++");
            }
        }).start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            flag=false;
        }).start();
    }
}
