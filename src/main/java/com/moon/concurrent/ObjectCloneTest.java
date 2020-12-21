package com.moon.concurrent;


import java.io.*;

class Moon implements Cloneable,Serializable {
    /**
     * String 是final修饰是常量
     */
    private String name;
    /**
     * 基本数据类型
     */
    private int age;
    /**
     * 引用对象
     */
    private StringBuffer remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StringBuffer getRemark() {
        return remark;
    }

    public void setRemark(StringBuffer remark) {
        this.remark = remark;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        /**
         * 把序列对象存储在内存中
         */
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            /**
             * 这里我需要吧对象写进来,this这个是那个执行的clone方法,this就是那个
             * 要想实现序列化还需要实现序列化接口
             */
            oos.writeObject(this);
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Moon{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", remark=" + remark +
                '}';
    }
}


public class ObjectCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Moon moon = new Moon();
        moon.setAge(30);
        moon.setName("taiyang");
        moon.setAge(30);
        moon.setRemark(new StringBuffer("洛书"));
        System.out.println(moon);
        System.out.println("==================================================");
        Moon clone = (Moon) moon.clone();
        clone.setName("混沌");
        clone.getRemark().append("河图");
        System.out.println(clone);
        System.out.println(moon);
        System.out.println(moon == clone);
        System.out.println(moon.getName() == clone.getName());
        System.out.println(moon.getRemark() == clone.getRemark());


    }
}
