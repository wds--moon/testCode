package com.moon.concurrent;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    
    public static void main(String[] args) {
        LinkedHashMap<String,Integer> map = new LinkedHashMap(5, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size()>5;
            }
        };
        for (int i = 0; i <10 ; i++) {
            map.put("key_"+i,i);
        }
        System.out.println(map);
        map.put("key_"+3,31);
        map.put("key_"+7,31);
        map.put("key_"+8,31);
        map.put("key_"+1,31);
        System.out.println(map);

        Iterator iterator = map.entrySet().iterator();
       while (iterator.hasNext()){
           System.out.println(iterator.next());
       }

    }
}
