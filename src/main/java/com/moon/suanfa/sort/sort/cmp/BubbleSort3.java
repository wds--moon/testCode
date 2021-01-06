package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 冒泡排序 每次循环都需要选择出最大的值,放到数组的最后面,数组操作长度,循环一次减小一次交换次数平凡,
 * 选择排序 是选择一个最大的,只是交换一次
 *
 * @param <T>
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        int size = array.length - 1;
        /**
         *
         *  这个算法对于后半部分有序会有非常明显的提升
         */
        for (int begin = size; begin > 0; begin--) {
            int index = 1;
            for (int start = 1; start <= begin; start++) {
                if (cmp(array[start], array[start - 1]) < 0) {
                    swap(start, start - 1);
                    index = start;
                }
            }
            begin = index;

        }
    }
}
