package com.moon.observer.jdk;

import java.util.Observable;

public class TencentObservable extends Observable{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        setChanged();
        /**
         * 可以传递参数
         */
        notifyObservers(title);
    }


}
