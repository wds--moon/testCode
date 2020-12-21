package com.moon.pk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeriableSingletonTest {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        FileOutputStream fileOutputStream;
        try {
            /**
             * 把字节文件序列化为新文件
             */
            fileOutputStream=new FileOutputStream("singleton.moon");
            ObjectOutputStream oos=new ObjectOutputStream(fileOutputStream);
            oos.writeObject(instance);
            oos.flush();
            oos.close();

            /**
             * 反序列
             */
            FileInputStream fileInputStream=new FileInputStream("singleton.moon");

            ObjectInputStream ois=new ObjectInputStream(fileInputStream);
            //反序列出来的新对象
            Singleton object = (Singleton)ois.readObject();
            ois.close();
            System.out.println(object);
            System.out.println(instance);
            System.out.println(instance==object);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
