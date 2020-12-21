package com.moon;

import java.util.*;

/**
 * 随着key的增大,map和arrayList 存储的值会无限趋近
 */
public class Demo {


    public static void main(String[] args) {

        setMap();

    }

    private static void setMap() {
        Map<String,String> map=new HashMap<>();
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            String str=UUID.randomUUID()+"奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    ",进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n"+UUID.randomUUID();
            System.out.println(map.size());
            map.put(String.valueOf(i),str);
        }
    }

    private static void setList() {
        List<String> list = new ArrayList<>();

        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            String str=UUID.randomUUID()+"奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    ",进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n" +
                    "奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能奖补类驳回,进行中缺少删除功能驳回,进行中缺少编辑功能\n"+UUID.randomUUID();
            System.out.println(list.size());
            list.add(str);
        }
    }
}
