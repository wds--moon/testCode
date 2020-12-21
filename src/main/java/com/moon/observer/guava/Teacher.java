package com.moon.observer.guava;

import com.google.common.eventbus.Subscribe;

public class Teacher {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * 添加注解
     * @param question
     */
    @Subscribe
    public void answer(Question question) {
        System.out.println(this.name+"老师,学生:"+question.getName()+" 提问,"+question.getTitle());
    }

    /**
     * 添加注解
     * @param tipOffs 举报
     */
    @Subscribe
    public void tipOffs(TipOffs tipOffs) {
        System.out.println(this.name+"老师,学生:"+tipOffs.getName()+" 提问,"+tipOffs.getTitle());
    }
}
