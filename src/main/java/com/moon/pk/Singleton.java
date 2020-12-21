package com.moon.pk;

import java.io.Serializable;

/**
 * 让其可序列化
 */
public class Singleton  implements Serializable {
    private Singleton() {
        if (LazySingle.SINGLETON != null) {
            throw new RuntimeException("不允许反射实例化单例!");
        }
    }

    public static final class LazySingle {
        public static Singleton SINGLETON = new Singleton();
    }

    public final static Singleton getInstance() {
        return LazySingle.SINGLETON;
    }


    private Object readResolve(){
        return  LazySingle.SINGLETON;
    }

}
