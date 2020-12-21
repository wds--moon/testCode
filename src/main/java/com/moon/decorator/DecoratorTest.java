package com.moon.decorator;


interface HandPancake {

    String pancake();

    Double calcCost();
}


/**
 * 最开始的手抓饼初始价格3.0元
 */
class OriginalHandPancake implements HandPancake {

    @Override
    public String pancake() {
        return "手抓饼";
    }

    @Override
    public Double calcCost() {
        return 3.0;
    }
}


/**
 * 现在需要增强类新增 加鸡蛋,加烤肠,加卷心菜
 */

/**
 * 配菜
 */
abstract class BaisideDish implements HandPancake {

    private HandPancake handPancake;

    public BaisideDish(HandPancake handPancake) {
        this.handPancake = handPancake;
    }


    @Override
    public String pancake() {
        return handPancake.pancake();
    }

    @Override
    public Double calcCost() {
        return handPancake.calcCost();
    }
}

/**
 * 鸡蛋 2.0
 */
class Egg extends BaisideDish{

    public Egg(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String pancake() {
        return super.pancake()+" 鸡蛋";
    }

    @Override
    public Double calcCost() {
        return super.calcCost()+2.0;
    }
}

/**
 * 烤肠 3.0
 */
class Sausages extends BaisideDish{

    public Sausages(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String pancake() {
        return super.pancake()+" 烤肠";
    }

    @Override
    public Double calcCost() {
        return super.calcCost()+3.0;
    }
}

/**
 * 卷心菜 1.0
 */
class Cabbage extends BaisideDish{

    public Cabbage(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String pancake() {
        return super.pancake()+" 卷心菜";
    }

    @Override
    public Double calcCost() {
        return super.calcCost()+1.0;
    }
}



public class DecoratorTest {

    public static void main(String[] args) {
        OriginalHandPancake originalHandPancake=new OriginalHandPancake();
        System.out.println(originalHandPancake.pancake()+" : "+ originalHandPancake.calcCost());
        /**
         * 加鸡蛋
         */
        Egg egg=new Egg(originalHandPancake);
        System.out.println(egg.pancake()+" : "+ egg.calcCost());

        /**
         * 加烤肠
         */
        Sausages sausages=new Sausages(egg);
        System.out.println(sausages.pancake()+" : "+ sausages.calcCost());

        /**
         * 加卷心菜
         */
        Cabbage cabbage=new Cabbage(sausages);
        System.out.println(cabbage.pancake()+" : "+ cabbage.calcCost());
    }

}
