package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 选择排序是选择一个最大的保留他的索引位置,内层循环结束就交换,相对于冒泡排序就减少了交换次数,时间复杂度依然不变
 * @param <T>
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        int size = array.length - 1;
        for (int begin = size; begin > 0; begin--) {
            int index=0;
            for (int start = 1; start <= begin; start++) {
                if (cmp(index, start) <0) {
                    index=start;
                }
            }
            swap(index, begin);
        }
    }
}
