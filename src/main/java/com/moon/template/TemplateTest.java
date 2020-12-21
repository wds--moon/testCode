package com.moon.template;

/**
 * 定义一个游戏抽象类,初始化,开始,结束都不实现,
 * 但是提供一个pay方法控制全局执行流程,
 * 子类不管如何实现都遵循这个流程
 */
abstract class Game {

     private void init(){
         System.out.println("游戏初始化开始,画面加载中...........");
     }

    abstract void start();

    abstract void end();

    public void pay() {
        init();
        start();
        end();
        System.out.println("游戏结束..........");
    }
}

class Tank extends  Game{

    @Override
    void start() {
        System.out.println("坦克大战开始!");
    }
    @Override
    void end() {
        System.out.println("你已经死了,请重新开始!");
    }
}

public class TemplateTest {
    public static void main(String[] args) {
        Tank tank=new Tank();
        tank.pay();
    }
}
