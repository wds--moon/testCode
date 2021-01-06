package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 冒泡排序 每次循环都需要选择出最大的值,放到数组的最后面,数组操作长度,循环一次减小一次交换次数平凡,
 * 选择排序 是选择一个最大的,只是交换一次
 *
 * @param <T>
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        int size = array.length - 1;
        /**
         * 冒泡排序 交换平凡
         */
        for (int begin = size; begin > 0; begin--) {
            for (int start = 1; start <= begin; start++) {
                if (cmp(array[start], array[start - 1]) < 0) {
                    swap(start, start - 1);
                }
            }
        }
    }
}
