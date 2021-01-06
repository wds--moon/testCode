package com.moon.suanfa.heap;

import java.util.Comparator;

public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;

    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * 设置比较
     * @param e1
     * @param e2
     * @return
     */
    public int compare(E e1,E e2){
        return comparator==null?((Comparable<E>)e1).compareTo(e2):comparator.compare(e1,e2);
    }

}
