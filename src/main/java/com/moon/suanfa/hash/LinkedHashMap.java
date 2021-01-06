package com.moon.suanfa.hash;

/**
 * 实现插入有序.其实就是在新增删除的时候维护一个链表链接起来
 * 当然这个链表也是可以维护的,可以控制链表长度,从而实现lRU
 *
 * @param <K>
 * @param <V>
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {

    /**
     * 指定缓存长度
     */
    private Integer length;

    //定义头和尾
    LinkedNode<K, V> head, tail;

    public LinkedHashMap() {
        this(Integer.MAX_VALUE);
    }

    public LinkedHashMap(Integer length) {
        this.length = length;
    }

    /**
     * 新增节点
     * @param key
     * @param value
     * @param parent
     * @return
     */
    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode node = new LinkedNode(key, value, parent);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return node;
    }

    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) {
        //需要被删除的
        LinkedNode<K, V> node1 = (LinkedNode<K, V>) willNode;
        //实际删除
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) removedNode;
        //交换位置.这个过程实际是在还原连表原理的样子,因为红黑树删除的都不是删除实际值,而是替换后删除叶子节点
        if (node1 != node2) {
            // 交换prev
            LinkedNode<K, V> tmp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev == null) {
                //删除的是头结点
                head = node1;
            } else {
                node1.prev.next = node1;
            }
            if (node2.prev == null) {
                //删除的是头结点
                head = node2;
            } else {
                node2.prev.next = node2;
            }
            // 交换next
            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;

            if (node1.next == null) {
                tail = node1;
            } else {
                node1.next.prev = node1;
            }
            if (node2.next == null) {
                tail = node2;
            } else {
                node2.next.prev = node2;
            }

        }
        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;
        //移除被删除节点
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

    }

    public static class LinkedNode<K, V> extends Node<K, V> {
        //双向连表维持前后链接
        LinkedNode<K, V> prev, next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

}
