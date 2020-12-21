package com.moon.designpattern.simper;


public class SimperFactory {

    public  static Car produce(Integer num){
        Car car=null;
        switch (num){
            case 1: car=new Benz();break;
            case 2: car=new Chery();break;
        }
        return car;
    }
}
