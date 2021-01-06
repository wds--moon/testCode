package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 插入排序 遍历数组 在当前子集中进行,从最后元素开始比较大小并且交换
 * 使用二分查找,减少了比较次数
 *
 * @param <T>
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        int length = array.length;
        for (int begin = 0; begin < length; begin++) {
            //插入到指定位置
            insert(begin, search(begin));
        }
    }

    /**
     * 元素向后移动,在指定位置插入元素
     *
     * @param begin
     * @param index
     */
    private void insert(int begin, int index) {
        for (int i = begin; i > index; i--) {
            //元素后移
            swap(i, i - 1);
        }
    }

    /**
     * 返回插入指定的位置索引
     * 折半查找法. 开始指针和结束指针 大于mid的值的时候开始指针变成mid,小于mid的值的时候end指针等于mid+1
     *
     * @param index
     * @return
     */
    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
