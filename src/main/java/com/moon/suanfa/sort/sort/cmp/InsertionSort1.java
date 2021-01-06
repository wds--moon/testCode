package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 插入排序 遍历数组 在当前子集中进行,从最后元素开始比较大小并且交换
 *
 * @param <T>
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            //小于0说明后面一个元素比前一个元素小,需要进行交换
            for (int index = i; index > 0; index--) {
                if (cmp(array[index], array[index - 1]) < 0) {
                    T tmp = array[index];
                    array[index] = array[index - 1];
                    array[index - 1] = tmp;
                }
            }

        }
    }
}
