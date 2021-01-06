package com.moon.suanfa.hash;

import com.moon.suanfa.二叉树.printer.BinaryTrees;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        setLinkedHashMap();

    }

    /**
     * 关于扩容存在 当数组商都未达到上限64的时候,如果一个槽中存储数据大于8的时候
     * 判断{
     * 插入=8 连表数据等于7 连表线等于6不满足 binCount >= TREEIFY_THRESHOLD - 1 继续等待添加,不会发生连表转红黑树
     * 插入=9  连表数据变成8 满足等式 binCount >= TREEIFY_THRESHOLD - 1 binCount此时等于7 ,由于数组大小还是小于64 所以进行扩容 16->32
     * 插入=10 连表数据变成9 满足等式 binCount >= TREEIFY_THRESHOLD - 1 binCount此时等于8 由于数组大小还是小于64 所以进行扩容 32->64
     * 到此时数据还是连表,并且节点个数已经变成了10
     * 插入=11 连表数据变成10 个原始,并且数据大小等于64 binCount=9 满足连表转红黑树的要求 转换为红黑树,所以说并不是连表数据大于等于8的时候进行扩容,还需要看他数组个数是否大于64
     * 小于64的时候 需要进行扩容 这样的话如果是32扩容到64 那么最小需要9个节点才满足,插入10个元素时候扩容
     * 如果是16-32-64 那么需要最小10个元素才满足,当插入11个的时候才真正红转黑
     * 当数组大小直接是64以上,那么当插入9的时候才进行红转黑,综上所述连表中最小需要>8个元素的时候才会进行红转黑
     * <p>
     * }
     */
    private static void save() {
        HashMap<User, Integer> hashMap = new HashMap();

        for (int i = 1; i < 120; i++) {
            if (i >= 8) {
                hashMap.put(new User(i), i);
            } else {
                hashMap.put(new User(i), i);
            }


        }
    }

    /**
     * 删除的时候发现树高是UNTREEIFY_THRESHOLD/2 的时候才进行树转连表
     * 当然在resize() ->split() 方法中如果发现 树节点<=UNTREEIFY_THRESHOLD 的是就会树转连表相反就转换为树
     */
    private static void remove() {
        HashMap<User, Integer> hashMap = new HashMap(64);
        for (int i = 1; i < 20; i++) {
            hashMap.put(new User(i), i);
        }
        for (int i = 0; i < 20; i++) {
            if (i >= 10) {
                hashMap.remove(new User(i));
            } else {
                hashMap.remove(new User(i));
            }
        }
        System.out.println(new User(1).equals(new User(1)));
        System.out.println(new User(1).hashCode() == new User(1).hashCode());
    }

    private static void setLinkedHashMap() {
        LinkedHashMap<User, Integer> map = new LinkedHashMap();
        for (int i = 1; i < 20; i++) {
            map.put(new User(i), i);
        }
        map.remove(new User(2));
        LinkedHashMap.LinkedNode<User, Integer> index = map.head;

        while (index != null) {
            System.out.println(index.key + "_" + index.value);
            index = index.next;
        }
    }
}
