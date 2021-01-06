package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

import java.util.Random;

/**
 * 快排 选择第一个点当轴心点,思想是不断切割把小的放左边,大的放右边,
 * 重复上述步骤,递归结束那么排序结束
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        int size = array.length;
        if (size < 2) {
            return;
        }
        sort(0, size);

    }

    private void sort(int begin, int end) {
        if (end - begin < 1) {
            return;
        }
        int axisIndex = getAxis(begin, end);
        sort(begin, axisIndex);
        sort(axisIndex + 1, end);
    }

    /**
     * 5,8,9,6,3,4,6,1,7
     * 5,8,9,6,3,4,6,1,7  li=0 ri=8
     * 1,8,9,6,3,4,6,1,7  li=1 ri=8
     * 1,8,9,6,3,4,6,8,7  li=1 ri=7
     * 1,8,9,6,3,4,6,1,7  li=1 ri=6
     * 1,4,9,6,3,4,6,1,7  li=2 ri=6
     * 1,4,9,6,3,9,6,1,7  li=2 ri=5
     * 1,4,3,6,3,9,6,1,7  li=3 ri=4
     * 1,4,3,6,6,9,6,1,7  li=3 ri=3
     * 第一轮结束:使用轴元素覆盖li或者ri
     * 1,4,3,5,6,9,6,1,7  li=3 ri=3
     *
     * @param begin
     * @param end
     * @return
     */
    private int getAxis(int begin, int end) {
        //初始元素进行备份,这样这个位置就空出来作为交换,但是为了减少最坏情况的时间复杂度,取值的时候采用随机值和第一个元素进行交换

        int rd = new Random().nextInt(end - begin) + begin;
        swap(begin, rd);
        T axisData = array[begin];
        int li = begin;
        int ri = end - 1;
        /**
         * 由于先从右边向左边比较,如果发现右边大ri--,如果右边就需要覆盖li元素,并且变相为左向右
         * 从左向右 如果发现比较小li++, 否则覆盖ri处索引的元素,并且换方向
         * 如此交替执行,由于是交替执行过程,并且存在连续ri--和li++就不能使用flag来使用,只能是死循环,转向就break
         */

        while (li < ri) {

            while (li < ri) {
                if (cmp(array[ri], axisData) > 0) {
                    ri--;
                } else {
                    array[li++] = array[ri];
                    //转向
                    break;
                }
            }
            while (li < ri) {
                if (cmp(array[li], axisData) < 0) {
                    li++;
                } else {
                    array[ri--] = array[li];
                    //转向
                    break;
                }
            }
        }
        //设置轴心
        array[li] = axisData;
        return li;
    }

}
