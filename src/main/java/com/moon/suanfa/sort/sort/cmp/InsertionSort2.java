package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 插入排序 遍历数组 在当前子集中进行,从最后元素开始比较大小并且交换
 * 改进如下:先记录需要插入的元素,合子集比较由于子集是有序的,只需要移动子集中的元素即可
 *
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            //小于0说明后面一个元素比前一个元素小,需要进行交换
            T v = array[i];
            int start = i;
            for (int index = i; index > 0; index--) {
                if (cmp(array[index-1],v) > 0) {
                    //找到位置还需要挪到元素
                    array[index] = array[index - 1];
                    start = index - 1;
                }
            }
            //start 说明已经找到位置,只需要进行插入
            array[start] = v;
        }
    }
}
