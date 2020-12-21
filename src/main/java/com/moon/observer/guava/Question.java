package com.moon.observer.guava;

/**
 * 论坛回答问题,@ 人回答的时候
 */
public class Question {
    private String name;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
