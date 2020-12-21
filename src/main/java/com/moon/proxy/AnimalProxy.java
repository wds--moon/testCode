package com.moon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnimalProxy implements InvocationHandler {

    //代理对象
    private Object obj;

    public Object getInstance(Object obj){
        this.obj=obj;
        /**
         * 当前代理对象的ClassLoader 当前类的接口(jdk动态代理是基于接口的所以必须有接口),当前代理对象InvocationHandler
         */
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前...................");
        /**
         * 传递是代理对象
         */
        Object invoke = method.invoke(obj, args);
        System.out.println("调用后...................");
        return invoke;
    }

}
