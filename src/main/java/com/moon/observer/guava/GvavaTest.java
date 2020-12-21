package com.moon.observer.guava;

import com.google.common.eventbus.EventBus;

import java.util.UUID;

public class GvavaTest {
    public static void main(String[] args) {
        Question question=new Question();
        question.setName("张三");
        question.setTitle(" 类加载原理?");
        Teacher teacher1=new Teacher("隔壁老王");
        Teacher teacher2=new Teacher("cat");

        EventBus bus=new EventBus();
        /**
         * 把被监听者加入消息中
         */
        bus.register(teacher1);
        bus.register(teacher2);
        bus.post(question);
        System.out.println("====================================");
        TipOffs offs=new TipOffs(UUID.randomUUID().toString()," 这节课没听懂 老师讲得不清楚.");
        bus.unregister(teacher1);
        bus.post(offs);
    }
}
