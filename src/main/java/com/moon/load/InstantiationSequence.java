package com.moon.load;

class TaiChi{

    private static TaiChi taiChi=new TaiChi();

    private  static double age = 100d;

    private  int score = 800;

    static {
        System.out.println("TaiChi static块");
    }

    {
        System.out.println("TaiChi构造块");
    }

    public TaiChi() {
        System.out.println("TaiChi构造器执行");
        System.out.println("TaiChi age=" + age + ": score=" + score);
    }
}

/**
 * 实例化顺序 先父类静态代码块静态属性->子类静态代码块静态属性->
 *  -> private static TaiChi taiChi=new TaiChi();-> private  int score = 800 ->TaiChi构造块->TaiChi构造器执行 age=0:score=800->
 *  private  static int age = 100 -> TaiChi  static块 到这里父类静态实例化完成
 *   Moon 静态实例化开始
 *   由于new Moon() moon是TaiChi 子类,此时会先调研一次TaiChi构造块和构造器,这个时候引起父类构造块和属性赋值所以是 TaiChi构造块-> TaiChi构造器执行 age=10:score=800
 *  -> Moon 构造块 -> Moon age=0: score=30 -> Moon  static块
 *  到这里静态属性和静态块执行完成了,应该走构造代码块和构造器
 *  -> 构造方法初始化顺序 先父类在子类
 *  TaiChi构造块-> TaiChi构造器执行->TaiChi age=100: score=800 ->
 *  Moon 构造块->Moon 构造器执行-> Moon age=30: score=80
 */

class Moon  extends  TaiChi{


    private static Moon moon = new Moon();


    static {
        System.out.println("Moon  static块");
    }

    private  static int age = 30;
    /**
     * =========================分割线===============================
     */
    private  int score = 80;


    {
        System.out.println("Moon 构造块");
    }

    public Moon() {
        System.out.println("Moon 构造器执行");
        System.out.println("Moon age=" + age + ": score=" + score);
    }

}


public class InstantiationSequence {
    public static void main(String[] args) {
        new Moon();
        System.out.println("==============================================");
    }
}
