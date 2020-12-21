package com.moon.observer.guava;

public class TipOffs {

    private String name;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TipOffs(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return "TipOffs{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
