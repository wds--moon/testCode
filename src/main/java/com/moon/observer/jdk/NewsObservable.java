package com.moon.observer.jdk;

import java.util.Observable;

/**
 * 新闻发布者(这就是被观察者)
 */
public class NewsObservable extends Observable{

    /**
     * 新闻标题
     */
    private String title;

    public String getTitle() {
        return title;
    }

    /**
     * 数据发生变化的时候需要通知观察者,把变化的数据发生给观察者
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
        this.setChanged();
        this.notifyObservers(title);
    }

}
