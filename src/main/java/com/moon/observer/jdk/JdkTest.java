package com.moon.observer.jdk;

public class JdkTest {

    public static void main(String[] args) {
        NewsObservable news=new NewsObservable();
        TencentObservable tencent=new TencentObservable();
        news.addObserver(new  UserObserver("张三"));
        news.addObserver(new  UserObserver("李四"));
        news.addObserver(new  UserObserver("王五"));
        news.addObserver(new  UserObserver("陈晨"));
        news.setTitle("你真的懂观察者模式么?");

        tencent.addObserver(new  UserObserver("陈晨"));
        tencent.addObserver(new  UserObserver("王五"));

        tencent.setTitle("和平精英游戏开始.....?");


    }
}
