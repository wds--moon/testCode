package com.moon.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 订阅的主题接口
 */
interface Subject {
    /**
     * 新增订阅者
     *
     * @param observer
     */
    void add(Observer observer);

    void remove(Observer observer);

    String getMsg();

}

/**
 * 订阅游戏
 */
class Game implements Subject {

    List<Observer> observers = new ArrayList<>();

    public String msg;

    public Game() {

    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        /**
         * 新增一个消息就发送给订阅者
         */
        notifyAllObserver();
    }

    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update(this.msg);
        }
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }
}


/**
 * 订阅游戏
 */
class History implements Subject {

    List<Observer> observers = new ArrayList<>();

    public String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        /**
         * 新增一个消息就发送给订阅者
         */
        notifyAllObserver();
    }

    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update(this.msg);
        }
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }
}

/**
 * 观察者,起到关联上下的作用,监控主题和用户
 */
abstract class Observer {

    protected Subject subject;

    public abstract void addSubject(Subject subject);

    public abstract void removeSubject(Subject subject);

    public abstract void update(String msg);
}

/**
 * 用户
 */
class User extends Observer {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void addSubject(Subject subject) {

        this.subject = subject;
        this.subject.add(this);
    }

    @Override
    public void removeSubject(Subject subject) {
        this.subject.remove(this);
    }

    @Override
    public void update(String msg) {
        System.out.println(name + " : " + msg);
    }
}

public class ObserverTest {
    public static void main(String[] args) {
        /**
         * 创建一个微信公众号
         */
        Game game = new Game();
        History history = new History();
        /**
         * 创建二个用户并且指定关注公众号
         */
        User user1 = new User("张飞");
        User user2 = new User("赵云");
        User user3 = new User("马超");
        user1.addSubject(game);
        user1.addSubject(history);
        /**
         * ===================================================
         */
        user2.addSubject(game);
        user2.addSubject(history);

        user3.addSubject(game);

        game.setMsg("dota2 已经没人玩了!");
        user2.removeSubject(history);
        history.setMsg("秦始皇身世之谜!");


    }
}
