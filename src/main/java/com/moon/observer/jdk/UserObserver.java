package com.moon.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者和订阅者
 */
public class UserObserver  implements Observer{
    /**
     * 观察者姓名
     */
    private String name;

    public UserObserver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        //arg 这个参数必须标准话,不能新闻是个string,腾讯是个map 那样的话这里需要做类型匹配转换,策略模式
        System.out.println(this.name+":"+arg);
    }
}
