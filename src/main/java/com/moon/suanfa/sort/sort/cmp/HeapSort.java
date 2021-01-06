package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 借助二叉堆特性进行堆排序,可以减少时间复杂度为nlogn,因为堆的下溢复杂度是logn
 * 1 根据堆排序的特点根节点就是第一个元素,所以我们需要找到最大的一个元素把他交换到array.length-1位置,这样最大的元素就在最后了,依次数组元素减一,循环处理直到只剩一个元素即可
 *
 * @param <T>
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {


    @Override
    protected void sort() {
        int heapSize = array.length;

        /**
         * 原地建堆,构建结束后就是一个堆,但是堆是一个相对排序,只有根节点才是最值,其他左右子树就不符合,所以需要循环处理
         */
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i, heapSize);
        }
        /**
         * size 每次就会减少一个,所以每次取堆顶元素即可,数组长度减一,排除已经排好序的元素
         */
        while (heapSize > 0) {
            swap(0, --heapSize);
            siftDown(0, heapSize);
        }

    }

    /**
     * 下溢
     *
     * @param index
     */
    private void siftDown(int index, int heapSize) {
        //得到分页子节点
        int half = heapSize >> 1;
        while (index < half) {
            int leftIndex = (index << 1) + 1;
            int rightIndex = leftIndex + 1;
            /**
             * 选出左右节点和根节点进行交换
             * 三种情况 存在左右节点都存在,存在左节点,左右节点相等还需要和父节点比较
             */
            if ((rightIndex < heapSize) && cmp(array[leftIndex], array[rightIndex]) >= 0) {
                if (cmp(array[leftIndex], array[index]) > 0) {
                    T tmp =array[leftIndex];
                    array[leftIndex] = array[index];
                    array[index] = tmp;
                    index = leftIndex;
                } else {
                    return;
                }

            } else if ((rightIndex < heapSize) && cmp(array[leftIndex], array[rightIndex]) < 0) {
                if (cmp(array[rightIndex], array[index]) > 0) {
                    T tmp = array[rightIndex];
                    array[rightIndex] = array[index];
                    array[index] = tmp;
                    index = rightIndex;
                } else {
                    return;
                }

            } else if ((rightIndex >= heapSize)) {
                //说明不存在右节点
                if (cmp(array[leftIndex], array[index]) > 0) {
                    T tmp =array[leftIndex];
                    array[leftIndex] = array[index];
                    array[index] = tmp;
                    index = leftIndex;
                } else {
                    return;
                }
            } else {
                return;
            }
        }

    }

}
